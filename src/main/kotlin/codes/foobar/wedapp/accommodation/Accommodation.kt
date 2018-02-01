package codes.foobar.wedapp.accommodation

import java.net.URL
import java.time.ZonedDateTime

class Accommodation(
        val id: Int,
        val name: String,
        val address: String,
        val phone: String,
        val homePage: URL,
        val infoHtml: String,
        val lastUpdated: ZonedDateTime
)