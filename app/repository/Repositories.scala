package repository

import domain.{User, Hotel, Wish, Guest}

trait Repositories {
  def guestRepository: GuestRepository
	def wishRepository: WishRepository
	def hotelRepository: HotelRepository
	def userRepository: UserRepository
}

trait GuestRepository {
  def saveGuest(guest: Guest)
	def updateGuest(guest: Guest)
	def deleteGuest(guest: Guest)
	def findAllAttending(): Seq[Guest]
	def findAll(): Seq[Guest]
}

trait WishRepository {
	def findAll: Seq[Wish]
}

trait HotelRepository {
	def findAll: Seq[Hotel]
}

trait UserRepository {
	def find(user: User): User
	def changePassword(user: User, newPassword: String): User
}