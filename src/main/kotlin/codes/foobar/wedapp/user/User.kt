package codes.foobar.wedapp.user

import codes.foobar.wedapp.role.Role
import java.time.ZonedDateTime

data class User(
        val id: Int,
        val name: String,
        val email: String,
        val roles: List<Role>,
        val updatedDateTime: ZonedDateTime
)