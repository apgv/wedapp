package codes.foobar.wedapp.guest

import java.time.ZonedDateTime

data class Guest(
        val id: Int,
        val fullName: String,
        val attending: Boolean,
        val note: String?,
        val createdDate: ZonedDateTime
)