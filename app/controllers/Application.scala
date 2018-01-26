package controllers

import play.api._
import mvc._
import libs.json._
import service.AppService

import org.json4s._
import org.json4s.jackson.JsonMethods.{parse => jparse}
import domain.Hotel
import scala.Some
import domain.User
import domain.Guest
import domain.Wish
import org.joda.time.format.ISODateTimeFormat
import org.bson.types.ObjectId
import org.joda.time.DateTime

object Application extends Controller with Secured {
	val LOG = Logger.logger

	def index = Action {
		Redirect(routes.Assets.at("app/index.html"))
	}

	var appService = new AppService

	def saveGuests = Action { request =>
		try {
			val body: AnyContent = request.body
			val json: Option[JsValue] = body.asJson

			json match {
				case Some(j) => {
					val firstNames = (j \\ "firstName").seq

					val guest1 = json2NewGuest(j(0))
					appService.saveGuest(guest1)

					if (firstNames.size > 1) {
						val guest2 = json2NewGuest(j(1))
						appService.saveGuest(guest2)
					}

					Created
				}
				case None => BadRequest
			}
		}
		catch {
			case e: Exception => {
				LOG.error(e.getStackTraceString)
				InternalServerError
			}
		}
	}

	def updateGuest = IsAdmin { username => request =>
		try {
			val body: AnyContent = request.body
			val json: Option[JsValue] = body.asJson

			json match {
				case Some(j) => {
					val guest = json2Guest(j)
					appService.updateGuest(guest)
					Ok
				}
				case None => BadRequest
			}
		}
		catch {
			case e: Exception => {
				LOG.error(e.getStackTraceString)
				InternalServerError
			}
		}
	}

	def deleteGuest = IsAdmin { username => request =>
		try {
			val body: AnyContent = request.body
			val json: Option[JsValue] = body.asJson

			json match {
				case Some(j) => {
					val guest = json2Guest(j)
					appService.deleteGuest(guest)
					Ok
				}
				case None => BadRequest
			}
		}
		catch {
			case e: Exception => {
				LOG.error(e.getStackTraceString)
				InternalServerError
			}
		}
	}

	def getWishlist = Action {
		try {
			val jsonWishlist = for (w <- appService.getWishlist) yield wish2Json(w)
			Ok(Json.toJson(jsonWishlist))
		} catch {
			case e: Exception => {
				LOG.error(e.getStackTraceString)
				InternalServerError
			}
		}
	}

	def getHotels = Action {
		try {
			val jsonHotelList = for (h <- appService.getHotels) yield hotel2Json(h)
			Ok(Json.toJson(jsonHotelList))
		}
		catch {
			case e: Exception => {
				LOG.error(e.getStackTraceString)
				InternalServerError
			}
		}
	}

	def getAttendingGuests = IsAuthenticated { username => request =>
		try {
			val jsonGuestList = for (g <- appService.getAttendingGuests) yield attendingGuest2Json(g)
			Ok(Json.toJson(jsonGuestList))
		} catch {
			case e: Exception => {
				LOG.error(e.getStackTraceString)
				InternalServerError
			}
		}
	}

	def getAllGuests = IsAdmin { username => request =>
		try {
			val jsonGuestList = for (g <- appService.getAllGuests) yield guest2Json(g)
			Ok(Json.toJson(jsonGuestList))
		} catch {
			case e: Exception => {
				LOG.error(e.getStackTraceString)
				InternalServerError
			}
		}
	}

	def changePassword = IsAuthenticated { username => request =>
		try {
			val body: AnyContent = request.body
			val json: Option[JsValue] = body.asJson

			json match {
				case Some(j) => {
					val oldPass = (j \ "oldPass").asOpt[String]
					val newPass = (j \ "newPass").asOpt[String]
					val newPassRepeated = (j \ "newPassRepeated").asOpt[String]

					val userFromDB = appService.changePassword(username, oldPass.get, newPass.get, newPassRepeated.get)
					Ok(user2Json(userFromDB)).withSession(("user" -> userFromDB.username))
				}
				case None => BadRequest
			}
		} catch {
			case e: Exception => {
				LOG.error(e.getStackTraceString)
				val errorMessage = Json.obj("error_message" -> e.getMessage)
				BadRequest(errorMessage)
			}
		}
	}

	def redirectToLogin = Action {
		Redirect(routes.Assets.at("app/index.html#/login"))
	}

	def login = Action { request =>
		try {
			val body: AnyContent = request.body
			val json: Option[JsValue] = body.asJson

			implicit val formats = DefaultFormats

			val parsedUser = jparse(json.get.toString())

			val user = parsedUser.extract[User]
			val userFromDB = appService.getUser(user)

			if (userFromDB.username.isEmpty) {
				Unauthorized.withNewSession
			} else {
				Ok(user2Json(userFromDB)).withSession("user" -> userFromDB.username, "role" -> userFromDB.role)
			}
		} catch {
			case e: Exception => {
				LOG.error(e.getStackTraceString)
				InternalServerError
			}
		}
	}

	def logout = Action {
		Ok.withNewSession
	}

	def e2e = Action {
		Redirect(routes.Assets.at("test/e2e/runner.html"))
	}

	implicit def hotel2Json(hotel: Hotel): JsObject = {
		Json.obj(
			"name" -> hotel.name,
			"address" -> hotel.address,
			"phone" -> hotel.phone,
			"email" -> hotel.email,
			"url" -> hotel.url)
	}

	implicit def wish2Json(wish: Wish): JsObject = {
		Json.obj("text" -> wish.text)
	}

	implicit def user2Json(user: User): JsObject = {
		Json.obj("username" -> user.username, "role" -> user.role)
	}

	implicit def attendingGuest2Json(guest: Guest): JsObject = {
		Json.obj(
			"firstName" -> guest.firstName,
			"lastName" -> guest.lastName,
			"relation" -> guest.relation,
			"about" -> guest.about)
	}

	implicit def guest2Json(guest: Guest): JsObject = {
		val formatter = ISODateTimeFormat.dateTime
		val formattedDate = guest.created.toString(formatter)

		Json.obj(
			"id" -> guest.id.toString,
			"firstName" -> guest.firstName,
			"lastName" -> guest.lastName,
			"email" -> guest.email,
			"attending" -> guest.attending,
			"note" -> guest.note,
			"relation" -> guest.relation,
			"about" -> guest.about,
			"created" -> formattedDate)
	}

	implicit def json2NewGuest(json: JsValue): Guest = {
		val firstName = (json \ "firstName").asOpt[String].get
		val lastName = (json \ "lastName").asOpt[String].get
		val email = (json \ "email").asOpt[String].getOrElse("")
		val attending = (json \ "attending").asOpt[Boolean].get
		val note = (json \ "note").asOpt[String].getOrElse("")

		new Guest(firstName, lastName, email, attending, note)
	}

	implicit def json2Guest(json: JsValue): Guest = {
		val idString = (json \ "id").asOpt[String].get
		val firstName = (json \ "firstName").asOpt[String].get
		val lastName = (json \ "lastName").asOpt[String].get
		val email = (json \ "email").asOpt[String].getOrElse("")
		val attending = (json \ "attending").asOpt[Boolean].get
		val note = (json \ "note").asOpt[String].getOrElse("")
		val relation = (json \ "relation").asOpt[String].getOrElse("")
		val about = (json \ "about").asOpt[String].getOrElse("")
		val createdString = (json \ "created").asOpt[String].get

		val created = new DateTime(createdString)
		val id = new ObjectId(idString)

		Guest(id, firstName, lastName, email, attending, note, relation, about, created)
	}

}