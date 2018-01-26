package service

import domain.{User, Hotel, Wish, Guest}
import repository.MongoDBRepositories
import play.api._

class AppService {

	val LOG = Logger.logger

  def saveGuest(guest: Guest) {
    val mongoRepo = new MongoDBRepositories

		mongoRepo.guestRepository.saveGuest(guest)
  }

	def updateGuest(guest: Guest) {
		val mongoRepo = new MongoDBRepositories

		mongoRepo.guestRepository.updateGuest(guest)
	}

	def deleteGuest(guest: Guest) {
		val mongoRepo = new MongoDBRepositories

		mongoRepo.guestRepository.deleteGuest(guest)
	}

	def getAttendingGuests: Seq[Guest] = {
		val mongoRepo = new MongoDBRepositories

		mongoRepo.guestRepository.findAllAttending()
	}

	def getAllGuests: Seq[Guest] = {
		val mongoRepo = new MongoDBRepositories

		mongoRepo.guestRepository.findAll()
	}

	def getWishlist: Seq[Wish] = {
		val mongoRepo = new MongoDBRepositories

		mongoRepo.wishRepository.findAll
	}

	def getHotels: Seq[Hotel] = {
		val mongoRepo = new MongoDBRepositories

		mongoRepo.hotelRepository.findAll
	}

	def getUser(user: User): User = {
		val mongorepo = new MongoDBRepositories

		mongorepo.userRepository.find(user)
	}

	def changePassword(username: String, oldPassword: String, newPassword: String, newPasswordRepeat: String): User = {
		val mongoRepo = new MongoDBRepositories

		if (newPassword.equals(newPasswordRepeat)) {
			val user = User(username, oldPassword)
			mongoRepo.userRepository.changePassword(user, newPassword)
		} else {
			LOG.info("Passwords don't match for user [" + username + "]")
			throw new Exception("De nye passordene er ikke like")
		}


	}

}
