package codes.foobar.wedapp.direction

import java.time.ZonedDateTime

data class Direction(
        val id: Int,
        val title: String,
        val content: String,
        val updatedDateTime: ZonedDateTime
)