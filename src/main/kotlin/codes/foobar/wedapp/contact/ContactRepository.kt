package codes.foobar.wedapp.contact

import codes.foobar.wedapp.helper.DbHelper
import codes.foobar.wedapp.jooq.tables.Contact.CONTACT

class ContactRepository(private val dbHelper: DbHelper) {

    fun findAll(): List<Contact> {
        return dbHelper.dslContext()
                .select(
                        CONTACT.ID,
                        CONTACT.NAME,
                        CONTACT.PHONE,
                        CONTACT.EMAIL,
                        CONTACT.LAST_UPDATED
                )
                .from(CONTACT)
                .fetch()
                .map {
                    Contact(
                            id = it[CONTACT.ID],
                            name = it[CONTACT.NAME],
                            phone = it[CONTACT.PHONE],
                            email = it[CONTACT.EMAIL],
                            updatedDateTime = it[CONTACT.LAST_UPDATED].toZonedDateTime()
                    )
                }
    }
}