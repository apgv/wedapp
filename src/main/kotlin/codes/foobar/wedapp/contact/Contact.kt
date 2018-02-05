package codes.foobar.wedapp.contact

import java.time.ZonedDateTime

data class Contact(
        val id: Int,
        val name: String,
        val email: String?,
        val phone: String?,
        val updatedDateTime: ZonedDateTime
)