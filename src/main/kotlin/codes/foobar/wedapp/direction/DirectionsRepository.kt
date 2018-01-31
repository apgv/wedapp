package codes.foobar.wedapp.direction

import codes.foobar.wedapp.helper.DbHelper
import codes.foobar.wedapp.jooq.tables.Direction.DIRECTION

class DirectionsRepository(private val dbHelper: DbHelper) {

    fun findAll(): List<Direction> {
        return dbHelper.dslContext()
                .select(
                        DIRECTION.ID,
                        DIRECTION.TITLE,
                        DIRECTION.CONTENT,
                        DIRECTION.LAST_UPDATED
                )
                .from(DIRECTION)
                .fetch()
                .map {
                    Direction(
                            id = it[DIRECTION.ID],
                            title = it[DIRECTION.TITLE],
                            content = it[DIRECTION.CONTENT],
                            updatedDateTime = it[DIRECTION.LAST_UPDATED].toZonedDateTime()
                    )
                }
    }
}