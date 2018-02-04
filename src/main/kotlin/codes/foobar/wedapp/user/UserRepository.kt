package codes.foobar.wedapp.user

import codes.foobar.wedapp.helper.DbHelper
import codes.foobar.wedapp.jooq.tables.Role.ROLE
import codes.foobar.wedapp.jooq.tables.User.USER
import codes.foobar.wedapp.jooq.tables.UserRole.USER_ROLE
import codes.foobar.wedapp.role.Role
import org.jooq.Record
import org.jooq.Result

class UserRepository(private val dbHelper: DbHelper) {

    private val selectParameters = listOf(
            USER.ID,
            USER.NAME,
            USER.EMAIL,
            USER.LAST_UPDATED,
            ROLE.ID,
            ROLE.NAME,
            ROLE.DESCRIPTION
    )

    fun findByEmail(email: String): User {
        return dbHelper.dslContext()
                .select(selectParameters)
                .from(USER)
                .leftJoin(USER_ROLE)
                .on(USER.ID.eq(USER_ROLE.USER_ID))
                .leftJoin(ROLE)
                .on(ROLE.ID.eq(USER_ROLE.ROLE_ID))
                .where(USER.EMAIL.eq(email))
                .fetchGroups(USER.ID)
                .values
                .map { mapUserWithRoles(it) }
                .first()
    }

    private fun mapUserWithRoles(result: Result<Record>): User {
        val roles = mapRoles(result)
        val record = result.first()
        return User(
                id = record[USER.ID],
                name = record[USER.NAME],
                email = record[USER.EMAIL],
                roles = roles,
                updatedDateTime = record[USER.LAST_UPDATED].toZonedDateTime()
        )
    }

    private fun mapRoles(result: Result<Record>): List<Role> {
        return result
                .filter { it[ROLE.ID] != null }
                .map {
                    Role(
                            id = it[ROLE.ID],
                            name = it[ROLE.NAME],
                            description = it[ROLE.DESCRIPTION]
                    )
                }
    }

}