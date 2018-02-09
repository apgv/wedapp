package codes.foobar.wedapp.gift

data class GiftRegistration(
        val id: Int?,
        val checkable: Boolean,
        val checked: Boolean,
        val title: String,
        val description: String?,
        val link: Link?
)