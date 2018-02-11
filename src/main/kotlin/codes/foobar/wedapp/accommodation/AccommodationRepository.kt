package codes.foobar.wedapp.accommodation

import codes.foobar.wedapp.helper.DbHelper
import codes.foobar.wedapp.helper.JavaTimeHelper
import codes.foobar.wedapp.jooq.Sequences.ACCOMMODATION_ID_SEQ
import codes.foobar.wedapp.jooq.tables.Accommodation.ACCOMMODATION
import org.jooq.Record
import org.jooq.TransactionalRunnable
import java.net.URL

class AccommodationRepository(private val dbHelper: DbHelper) {

    private val selectParameters = listOf(
            ACCOMMODATION.ID,
            ACCOMMODATION.NAME,
            ACCOMMODATION.ADDRESS,
            ACCOMMODATION.PHONE,
            ACCOMMODATION.HOME_PAGE,
            ACCOMMODATION.INFO,
            ACCOMMODATION.LAST_UPDATED
    )

    fun findAll(): List<Accommodation> {
        return dbHelper.dslContext()
                .select(selectParameters)
                .from(ACCOMMODATION)
                .orderBy(ACCOMMODATION.ID)
                .fetch()
                .map { mapToAccommodations(it) }
    }

    fun findById(id: Int): Accommodation {
        return dbHelper.dslContext()
                .select(selectParameters)
                .from(ACCOMMODATION)
                .where(ACCOMMODATION.ID.eq(id))
                .fetchOne()
                .map { mapToAccommodations(it) }
    }

    fun save(accommodationRegistration: AccommodationRegistration) {
        val dslContext = dbHelper.dslContext()

        dslContext.transaction(TransactionalRunnable {
            dslContext.insertInto(ACCOMMODATION,
                    ACCOMMODATION.ID,
                    ACCOMMODATION.NAME,
                    ACCOMMODATION.ADDRESS,
                    ACCOMMODATION.PHONE,
                    ACCOMMODATION.HOME_PAGE,
                    ACCOMMODATION.INFO,
                    ACCOMMODATION.LAST_UPDATED
            ).values(
                    dslContext.nextval(ACCOMMODATION_ID_SEQ).toInt(),
                    accommodationRegistration.name,
                    accommodationRegistration.address,
                    accommodationRegistration.phone,
                    accommodationRegistration.homePage?.toString(),
                    accommodationRegistration.infoHtml,
                    JavaTimeHelper.nowEuropeOslo()
            )
                    .execute()
        })
    }

    fun update(accommodationRegistration: AccommodationRegistration) {
        val dslContext = dbHelper.dslContext()

        dslContext.transaction(TransactionalRunnable {
            dslContext.update(ACCOMMODATION)
                    .set(ACCOMMODATION.NAME, accommodationRegistration.name)
                    .set(ACCOMMODATION.ADDRESS, accommodationRegistration.address)
                    .set(ACCOMMODATION.PHONE, accommodationRegistration.phone)
                    .set(ACCOMMODATION.HOME_PAGE, accommodationRegistration.homePage?.toString())
                    .set(ACCOMMODATION.INFO, accommodationRegistration.infoHtml)
                    .where(ACCOMMODATION.ID.eq(accommodationRegistration.id))
                    .execute()
        })
    }

    fun delete(id: Int) {
        val dslContext = dbHelper.dslContext()

        dslContext.transaction(TransactionalRunnable {
            dslContext.deleteFrom(ACCOMMODATION)
                    .where(ACCOMMODATION.ID.eq(id))
                    .execute()
        })
    }

    private fun mapToAccommodations(record: Record) =
            Accommodation(
                    id = record[ACCOMMODATION.ID],
                    name = record[ACCOMMODATION.NAME],
                    address = record[ACCOMMODATION.ADDRESS],
                    phone = record[ACCOMMODATION.PHONE].orEmpty(),
                    homePage = toURL(record[ACCOMMODATION.HOME_PAGE].orEmpty()),
                    infoHtml = record[ACCOMMODATION.INFO].orEmpty(),
                    lastUpdated = record[ACCOMMODATION.LAST_UPDATED].toZonedDateTime()
            )

    private fun toURL(homePage: String): URL? {
        return when {
            homePage.isNotBlank() -> URL(homePage)
            else -> null
        }
    }
}