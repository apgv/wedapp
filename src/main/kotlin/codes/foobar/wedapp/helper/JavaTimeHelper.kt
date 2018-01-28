package codes.foobar.wedapp.helper

import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.ZonedDateTime

object JavaTimeHelper {
    fun nowEuropeOslo(): OffsetDateTime =
            ZonedDateTime
                    .now(ZoneId.of("Europe/Oslo"))
                    .toOffsetDateTime()
}