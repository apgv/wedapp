package codes.foobar.wedapp.moshi

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.net.URL

class URLAdapter {
    @ToJson
    fun toJson(url: URL) = url.toString()

    @FromJson
    fun fromJson(url: String) = URL(url)
}