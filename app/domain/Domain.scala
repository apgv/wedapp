package domain

import org.joda.time.{DateTimeZone, DateTime}
import org.bson.types.ObjectId

case class Wish(text: String)

case class Hotel(
		name: String = "",
		address: String = "",
		phone: String = "",
		email: String = "",
		url: String = "")

case class Guest(
		id: ObjectId = null,
		firstName: String = "",
		lastName: String = "",
		email: String = "",
		attending: Boolean = false,
		note: String = "",
		relation: String = "",
		about: String = "",
		created: DateTime = new DateTime(DateTimeZone.forID("Europe/Oslo"))) {

	def this(firstName: String, lastName: String, email: String, attending: Boolean, note: String) =
		this(null, firstName, lastName, email, attending, note)
}

case class User(
		username: String = "",
		password: String = "",
		role: String = "")