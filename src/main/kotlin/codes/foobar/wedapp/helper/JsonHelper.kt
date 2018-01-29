package codes.foobar.wedapp.helper

import codes.foobar.wedapp.moshi.ZonedDateTimeAdapter
import com.squareup.moshi.Moshi

object JsonHelper {
    val moshi: Moshi = Moshi.Builder()
            .add(ZonedDateTimeAdapter())
            .build()
}