package codes.foobar.wedapp.index

import java.time.ZonedDateTime

data class IndexPage(
        val id: Int? = null,
        val content: String? = null,
        val updatedDateTime: ZonedDateTime? = null
)