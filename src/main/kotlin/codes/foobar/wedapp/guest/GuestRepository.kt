package codes.foobar.wedapp.guest

import codes.foobar.wedapp.helper.DbHelper
import codes.foobar.wedapp.helper.JavaTimeHelper
import codes.foobar.wedapp.jooq.Sequences.*
import codes.foobar.wedapp.jooq.tables.Guest.*
import org.jooq.TransactionalRunnable

class GuestRepository(private val dbHelper: DbHelper) {

    fun save(guestRegistrations: List<GuestRegistration>) {
        val dslContext = dbHelper.dslContext()

        dslContext.transaction(TransactionalRunnable {
            guestRegistrations.forEach {
                dslContext.insertInto(GUEST,
                        GUEST.ID,
                        GUEST.FULL_NAME,
                        GUEST.ATTENDING,
                        GUEST.NOTE,
                        GUEST.CREATED_DATE
                ).values(
                        dslContext.nextval(GUEST_ID_SEQ).toInt(),
                        it.fullName,
                        it.attending,
                        it.note,
                        JavaTimeHelper.nowEuropeOslo()
                )
                        .execute()
            }
        })
    }
}