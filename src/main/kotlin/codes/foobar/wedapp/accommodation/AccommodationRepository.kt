package codes.foobar.wedapp.accommodation

import codes.foobar.wedapp.helper.DbHelper
import codes.foobar.wedapp.jooq.tables.Accommodation.ACCOMMODATION
import java.net.URL

class AccommodationRepository(private val dbHelper: DbHelper) {

    fun findAll(): List<Accommodation> {
        return dbHelper.dslContext()
                .select(
                        ACCOMMODATION.ID,
                        ACCOMMODATION.NAME,
                        ACCOMMODATION.ADDRESS,
                        ACCOMMODATION.PHONE,
                        ACCOMMODATION.HOME_PAGE,
                        ACCOMMODATION.INFO,
                        ACCOMMODATION.LAST_UPDATED
                )
                .from(ACCOMMODATION)
                .fetch()
                .map {
                    Accommodation(
                            id = it[ACCOMMODATION.ID],
                            name = it[ACCOMMODATION.NAME],
                            address = it[ACCOMMODATION.ADDRESS],
                            phone = it[ACCOMMODATION.PHONE],
                            homePage = URL(it[ACCOMMODATION.HOME_PAGE]),
                            infoHtml = it[ACCOMMODATION.INFO],
                            lastUpdated = it[ACCOMMODATION.LAST_UPDATED].toZonedDateTime()
                    )
                }
    }
}