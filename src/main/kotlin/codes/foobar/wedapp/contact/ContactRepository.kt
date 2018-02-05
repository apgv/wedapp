package codes.foobar.wedapp.contact

import codes.foobar.wedapp.helper.DbHelper
import codes.foobar.wedapp.helper.JavaTimeHelper
import codes.foobar.wedapp.jooq.Sequences.CONTACT_ID_SEQ
import codes.foobar.wedapp.jooq.tables.Contact.CONTACT
import org.jooq.Record
import org.jooq.TransactionalRunnable

class ContactRepository(private val dbHelper: DbHelper) {

    private val selectParameters = listOf(
            CONTACT.ID,
            CONTACT.NAME,
            CONTACT.PHONE,
            CONTACT.EMAIL,
            CONTACT.LAST_UPDATED
    )

    fun findAll(): List<Contact> {
        return dbHelper.dslContext()
                .select(selectParameters)
                .from(CONTACT)
                .fetch()
                .map { mapContact(it) }
    }

    fun findById(id: Int): Contact {
        return dbHelper.dslContext()
                .select(selectParameters)
                .from(CONTACT)
                .where(CONTACT.ID.eq(id))
                .fetchOne()
                .map { mapContact(it) }
    }

    private fun mapContact(it: Record): Contact {
        return Contact(
                id = it[CONTACT.ID],
                name = it[CONTACT.NAME],
                phone = it[CONTACT.PHONE],
                email = it[CONTACT.EMAIL],
                updatedDateTime = it[CONTACT.LAST_UPDATED].toZonedDateTime()
        )
    }

    fun save(contactRegistration: ContactRegistration) {
        val dslContext = dbHelper.dslContext()

        dslContext.transaction(TransactionalRunnable {
            dslContext.insertInto(CONTACT,
                    CONTACT.ID,
                    CONTACT.NAME,
                    CONTACT.PHONE,
                    CONTACT.EMAIL,
                    CONTACT.LAST_UPDATED
            ).values(
                    dslContext.nextval(CONTACT_ID_SEQ).toInt(),
                    contactRegistration.name,
                    contactRegistration.phone,
                    contactRegistration.email,
                    JavaTimeHelper.nowEuropeOslo()
            )
                    .execute()
        })
    }

    fun update(contactRegistration: ContactRegistration) {
        val dslContext = dbHelper.dslContext()

        dslContext.transaction(TransactionalRunnable {
            dslContext.update(CONTACT)
                    .set(CONTACT.NAME, contactRegistration.name)
                    .set(CONTACT.PHONE, contactRegistration.phone)
                    .set(CONTACT.EMAIL, contactRegistration.email)
                    .set(CONTACT.LAST_UPDATED, JavaTimeHelper.nowEuropeOslo())
                    .where(CONTACT.ID.eq(contactRegistration.id))
                    .execute()
        })
    }
}