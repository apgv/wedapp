package codes.foobar.wedapp.direction

import codes.foobar.wedapp.helper.DbHelper
import codes.foobar.wedapp.helper.JavaTimeHelper
import codes.foobar.wedapp.jooq.Sequences
import codes.foobar.wedapp.jooq.tables.Direction.DIRECTION
import org.jooq.Record
import org.jooq.TransactionalRunnable

class DirectionsRepository(private val dbHelper: DbHelper) {

    private val selectParameters = listOf(
            DIRECTION.ID,
            DIRECTION.TITLE,
            DIRECTION.CONTENT,
            DIRECTION.LAST_UPDATED
    )

    fun findAll(): List<Direction> {
        return dbHelper.dslContext()
                .select(selectParameters)
                .from(DIRECTION)
                .fetch()
                .map { mapToDirection(it) }
    }

    fun findById(id: Int): Direction {
        return dbHelper.dslContext()
                .select(selectParameters)
                .from(DIRECTION)
                .where(DIRECTION.ID.eq(id))
                .fetchOne()
                .map { mapToDirection(it) }
    }

    fun save(directionRegistration: DirectionRegistration) {
        val dslContext = dbHelper.dslContext()

        dslContext.transaction(TransactionalRunnable {
            dslContext.insertInto(DIRECTION,
                    DIRECTION.ID,
                    DIRECTION.TITLE,
                    DIRECTION.CONTENT,
                    DIRECTION.LAST_UPDATED
            ).values(
                    dslContext.nextval(Sequences.DIRECTION_ID_SEQ).toInt(),
                    directionRegistration.title,
                    directionRegistration.content,
                    JavaTimeHelper.nowEuropeOslo()
            )
                    .execute()
        })
    }

    fun update(directionRegistration: DirectionRegistration) {
        val dslContext = dbHelper.dslContext()

        dslContext.transaction(TransactionalRunnable {
            dslContext.update(DIRECTION)
                    .set(DIRECTION.TITLE, directionRegistration.title)
                    .set(DIRECTION.CONTENT, directionRegistration.content)
                    .set(DIRECTION.LAST_UPDATED, JavaTimeHelper.nowEuropeOslo())
                    .where(DIRECTION.ID.eq(directionRegistration.id))
                    .execute()
        })
    }

    private fun mapToDirection(it: Record): Direction {
        return Direction(
                id = it[DIRECTION.ID],
                title = it[DIRECTION.TITLE],
                content = it[DIRECTION.CONTENT],
                updatedDateTime = it[DIRECTION.LAST_UPDATED].toZonedDateTime()
        )
    }
}