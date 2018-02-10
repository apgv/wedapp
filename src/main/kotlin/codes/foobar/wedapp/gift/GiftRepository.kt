package codes.foobar.wedapp.gift

import codes.foobar.wedapp.helper.DbHelper
import codes.foobar.wedapp.helper.JavaTimeHelper
import codes.foobar.wedapp.jooq.Sequences
import codes.foobar.wedapp.jooq.tables.Gift.GIFT
import org.jooq.Record
import org.jooq.TransactionalRunnable
import java.net.URL

class GiftRepository(private val dbHelper: DbHelper) {

    private val selectParameters = listOf(
            GIFT.ID,
            GIFT.CHECKABLE,
            GIFT.CHECKED,
            GIFT.TITLE,
            GIFT.DESCRIPTION,
            GIFT.LINK_URL,
            GIFT.LINK_TEXT,
            GIFT.LAST_UPDATED
    )

    fun findAll(): List<Gift> {
        return dbHelper.dslContext()
                .select(selectParameters)
                .from(GIFT)
                .orderBy(GIFT.ID)
                .fetch()
                .map { mapToGift(it) }
    }

    fun findById(id: Int): Gift {
        return dbHelper.dslContext()
                .select(selectParameters)
                .from(GIFT)
                .where(GIFT.ID.eq(id))
                .fetchOne()
                .map { mapToGift(it) }
    }

    fun save(giftRegistration: GiftRegistration) {
        val dslContext = dbHelper.dslContext()

        dslContext.transaction(TransactionalRunnable {
            dslContext.insertInto(GIFT,
                    GIFT.ID,
                    GIFT.CHECKABLE,
                    GIFT.CHECKED,
                    GIFT.TITLE,
                    GIFT.DESCRIPTION,
                    GIFT.LINK_URL,
                    GIFT.LINK_TEXT,
                    GIFT.LAST_UPDATED
            ).values(
                    dslContext.nextval(Sequences.GIFT_ID_SEQ).toInt(),
                    giftRegistration.checkable,
                    giftRegistration.checked,
                    giftRegistration.title,
                    giftRegistration.description,
                    giftRegistration.link?.url?.toString(),
                    giftRegistration.link?.text,
                    JavaTimeHelper.nowEuropeOslo()
            )
                    .execute()
        })
    }

    fun update(giftRegistration: GiftRegistration) {
        val dslContext = dbHelper.dslContext()

        dslContext.transaction(TransactionalRunnable {
            dslContext.update(GIFT)
                    .set(GIFT.CHECKABLE, giftRegistration.checkable)
                    .set(GIFT.TITLE, giftRegistration.title)
                    .set(GIFT.DESCRIPTION, giftRegistration.description)
                    .set(GIFT.LINK_URL, giftRegistration.link?.url.toString())
                    .set(GIFT.LINK_TEXT, giftRegistration.link?.text)
                    .set(GIFT.LAST_UPDATED, JavaTimeHelper.nowEuropeOslo())
                    .where(GIFT.ID.eq(giftRegistration.id))
                    .execute()
        })
    }

    fun updateChecked(id: Int, checked: Boolean) {
        val dslContext = dbHelper.dslContext()

        dslContext.transaction(TransactionalRunnable {
            dslContext.update(GIFT)
                    .set(GIFT.CHECKED, checked)
                    .set(GIFT.LAST_UPDATED, JavaTimeHelper.nowEuropeOslo())
                    .where(GIFT.ID.eq(id))
                    .execute()
        })
    }

    private fun mapToGift(it: Record): Gift {
        return Gift(
                id = it[GIFT.ID],
                checkable = it[GIFT.CHECKABLE],
                checked = it[GIFT.CHECKED],
                title = it[GIFT.TITLE],
                description = it[GIFT.DESCRIPTION],
                link = toLink(it[GIFT.LINK_URL], it[GIFT.LINK_TEXT]),
                updatedDateTime = it[GIFT.LAST_UPDATED].toZonedDateTime()
        )
    }

    private fun toLink(url: String?, linkText: String?): Link? {
        return when {
            url != null -> Link(URL(url), linkText.orEmpty())
            else -> Link()
        }
    }
}
