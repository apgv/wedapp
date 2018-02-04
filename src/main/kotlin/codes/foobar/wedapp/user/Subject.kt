package codes.foobar.wedapp.user

data class Subject(
        val name: String,
        val email: String,
        val roles: List<String>
)

fun toSubject(user: User) =
        Subject(
                name = user.name,
                email = user.email,
                roles = user.roles.map { it.name }
        )