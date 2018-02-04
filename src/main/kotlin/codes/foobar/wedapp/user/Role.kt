package codes.foobar.wedapp.user

enum class Role {
    ADMIN,
    USER;

    companion object {
        fun valueOfIgnoreCase(role: String): Role {
            return valueOf(role.toUpperCase())
        }
    }
}