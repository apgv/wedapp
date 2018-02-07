package codes.foobar.wedapp.accommodation

import java.net.URL

data class AccommodationRegistration(
        val id: Int?,
        val name: String,
        val address: String,
        val phone: String?,
        val homePage: URL?,
        val infoHtml: String?
)