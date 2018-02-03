package codes.foobar.wedapp.gift

import codes.foobar.wedapp.helper.DbHelper
import codes.foobar.wedapp.jooq.tables.Gift.GIFT
import java.net.URL

class GiftRepository(private val dbHelper: DbHelper) {

    fun findAll(): List<Gift> {
        return dbHelper.dslContext()
                .select(
                        GIFT.ID,
                        GIFT.CHECKABLE,
                        GIFT.CHECKED,
                        GIFT.TITLE,
                        GIFT.DESCRIPTION,
                        GIFT.LINK_URL,
                        GIFT.LINK_TEXT,
                        GIFT.LAST_UPDATED
                )
                .from(GIFT)
                .fetch()
                .map {
                    Gift(
                            id = it[GIFT.ID],
                            checkable = it[GIFT.CHECKABLE],
                            checked = it[GIFT.CHECKED],
                            title = it[GIFT.TITLE],
                            description = it[GIFT.DESCRIPTION],
                            link = Link(URL(it[GIFT.LINK_URL]), it[GIFT.LINK_TEXT]),
                            updatedDateTime = it[GIFT.LAST_UPDATED].toZonedDateTime()
                    )
                }
    }
}