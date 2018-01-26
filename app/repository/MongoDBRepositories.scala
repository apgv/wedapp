package repository

import util.Properties
import com.mongodb.casbah.Imports._
import com.mongodb.casbah.{MongoCollection, MongoConnection, MongoURI}
import com.mongodb.DBObject
import domain.{User, Hotel, Wish, Guest}
import com.mongodb.casbah.commons.conversions.scala._
import com.github.t3hnar.bcrypt._
import play.api._
import org.joda.time.DateTime

class MongoDBRepositories extends Repositories {

	RegisterJodaTimeConversionHelpers()

	val LOG = Logger.logger

  private lazy val db = Properties.envOrNone("MONGOLAB_URI") match {
    case Some(uri) => {
      val mongoURI: MongoURI = MongoURI(uri)
      val connection: MongoConnection = MongoConnection(mongoURI)

      val db = connection.getDB(mongoURI.database.get)
      db.authenticate(mongoURI.username.get, new String(mongoURI.password.get))
      connection(mongoURI.database.get)
    }
    case _ => {
      MongoConnection()("wedapp")
    }
  }

  lazy val guests: MongoCollection = db("guests")
	lazy val wishes: MongoCollection = db("wishes")
	lazy val hotels: MongoCollection = db("hotels")
	lazy val users: MongoCollection = db("users")

  lazy val guestRepository = new GuestRepository {
		val sortAscOnCreated = MongoDBObject("created" -> 1)

    def saveGuest(guest: Guest) {
      guests.save(guest2DbObject(guest))
    }

		def updateGuest(guest: Guest) {
			val q = MongoDBObject("_id" -> guest.id)

			guests.update(q, $set(Seq(
				"firstName" -> guest.firstName,
				"lastName" -> guest.lastName,
				"email" -> guest.email,
				"attending" -> guest.attending,
				"note" -> guest.note,
				"relation" -> guest.relation,
				"about" -> guest.about)))
		}


		def deleteGuest(guest: Guest) {
			val q = MongoDBObject("_id" -> guest.id)

			guests.remove(q)
		}

		def findAllAttending(): Seq[Guest] = {
			val q = MongoDBObject("attending" -> true)
			val x = for (g <- guests.find(q).sort(sortAscOnCreated)) yield dbObject2Guest(g)
			x.toSeq
		}

		def findAll(): Seq[Guest] = {
			val x = for (g <- guests.find().sort(sortAscOnCreated)) yield dbObject2Guest(g)
			x.toSeq
		}
	}

	lazy val wishRepository = new WishRepository {
		def findAll: Seq[Wish] = {
			val q = MongoDBObject("visible" -> true)
			val x = for (w <- wishes.find(q)) yield dbObject2Wish(w)
			x.toSeq
		}
	}

	lazy val hotelRepository = new HotelRepository {
		def findAll: Seq[Hotel] = {
			val x = for (h <- hotels.find()) yield dbObject2Hotel(h)
			x.toSeq
		}
	}

	lazy val userRepository = new UserRepository {
		def find(user: User): User = {
			val q = MongoDBObject("username" -> user.username)

			val u = users.findOne(q)

			u match {
				case Some(x) => {
					val passwordsMatch = user.password.isBcrypted(x.password)

					if (passwordsMatch) dbObject2User(x) else User()
				}
				case None => User()
			}
		}

		def changePassword(user: User, newPassword: String): User = {
			val q = MongoDBObject("username" -> user.username)

			val u = users.findOne(q)

			u match {
				case Some(x) => {
					val passwordMatch = user.password.isBcrypted(x.password)

					if (passwordMatch) {
						val id = MongoDBObject("_id" -> x.get("_id"))
						val encryptedPassword = newPassword.bcrypt
						users.update(id, $set(List("password" -> encryptedPassword)))
						LOG.info("Password updated for user [" + user.username + "]")
						dbObject2User(x)
					} else {
						LOG.info("Password is wrong for user [" + user.username + "]")
						throw new Exception("Feil passord")
					}
				}
				case None => User()
			}
		}
	}

	implicit def dbObject2Wish(dbObject: DBObject): Wish = {
		Wish(dbObject.getAs[String]("wish").get)
	}

	implicit def dbObject2Hotel(dbObject: DBObject): Hotel = {
		Hotel(
			dbObject.getAs[String]("name").getOrElse(""),
			dbObject.getAs[String]("address").getOrElse(""),
			dbObject.getAs[String]("phone").getOrElse(""),
			dbObject.getAs[String]("email").getOrElse(""),
			dbObject.getAs[String]("url").getOrElse(""))
	}

  implicit def guest2DbObject(guest: Guest): DBObject = {
    MongoDBObject(
      "firstName" -> guest.firstName,
      "lastName" -> guest.lastName,
      "email" -> guest.email,
      "attending" -> guest.attending,
      "note" -> guest.note,
			"created" -> guest.created)
  }

	implicit def dbObject2Guest(dbObject: DBObject): Guest = {
		Guest(
			dbObject.getAs[ObjectId]("_id").get,
			dbObject.getAs[String]("firstName").get,
			dbObject.getAs[String]("lastName").get,
			dbObject.getAs[String]("email").getOrElse(""),
			dbObject.getAs[Boolean]("attending").get,
			dbObject.getAs[String]("note").getOrElse(""),
			dbObject.getAs[String]("relation").getOrElse(""),
			dbObject.getAs[String]("about").getOrElse(""),
			dbObject.getAs[DateTime]("created").get)
	}

	implicit def dbObject2User(dbObject: DBObject): User = {
		User(
			dbObject.getAs[String]("username").get,
			dbObject.getAs[String]("password").get,
			dbObject.getAs[String]("role").getOrElse("NO_ROLE"))
	}

}
