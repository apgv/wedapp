package codes.foobar.wedapp.index

import java.time.ZonedDateTime

data class IndexPage(
        val id: Int?,
        val content: String,
        val updatedDateTime: ZonedDateTime?
)