package codes.foobar.wedapp.moshi

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

class ZonedDateTimeAdapter {
    @ToJson
    fun toJson(zonedDateTime: ZonedDateTime) = zonedDateTime.toInstant().toEpochMilli()

    @FromJson
    fun fromJson(date: String) = ZonedDateTime.ofInstant(Instant.ofEpochMilli(date.toLong()), ZoneId.of("Europe/Oslo"))
}