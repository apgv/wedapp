package codes.foobar.wedapp.gift

import java.time.ZonedDateTime

data class Gift(
        val id: Int,
        val checkable: Boolean,
        val checked: Boolean,
        val title: String,
        val description: String?,
        val link: Link?,
        val updatedDateTime: ZonedDateTime
)