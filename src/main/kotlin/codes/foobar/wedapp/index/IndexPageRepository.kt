package codes.foobar.wedapp.index

import codes.foobar.wedapp.helper.DbHelper
import codes.foobar.wedapp.helper.JavaTimeHelper
import codes.foobar.wedapp.jooq.Sequences
import codes.foobar.wedapp.jooq.tables.IndexPage.INDEX_PAGE
import org.jooq.TransactionalRunnable

class IndexPageRepository(private val dbHelper: DbHelper) {

    fun find(): IndexPage {
        return dbHelper.dslContext()
                .select(
                        INDEX_PAGE.ID,
                        INDEX_PAGE.CONTENT,
                        INDEX_PAGE.LAST_UPDATED
                )
                .from(INDEX_PAGE)
                .fetchOne()
                .map {
                    IndexPage(
                            id = it[INDEX_PAGE.ID],
                            content = it[INDEX_PAGE.CONTENT],
                            updatedDateTime = it[INDEX_PAGE.LAST_UPDATED].toZonedDateTime()
                    )
                }
    }

    fun save(indexPage: IndexPage) {
        val dslContext = dbHelper.dslContext()

        dslContext.transaction(TransactionalRunnable {
            if (indexPage.id == null) {

                dslContext.insertInto(INDEX_PAGE,
                        INDEX_PAGE.ID,
                        INDEX_PAGE.CONTENT,
                        INDEX_PAGE.LAST_UPDATED
                ).values(
                        dslContext.nextval(Sequences.INDEX_PAGE_ID_SEQ).toInt(),
                        indexPage.content,
                        JavaTimeHelper.nowEuropeOslo()
                )
                        .execute()
            } else {
                dslContext.update(INDEX_PAGE)
                        .set(INDEX_PAGE.CONTENT, indexPage.content)
                        .set(INDEX_PAGE.LAST_UPDATED, JavaTimeHelper.nowEuropeOslo())
                        .where(INDEX_PAGE.ID.eq(indexPage.id))
                        .execute()
            }
        })
    }

}