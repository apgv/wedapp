package codes.foobar.wedapp.config

import java.net.URI

class HerokuPostgresConfig(private val uri: URI) {
    fun username() = uri.userInfo.split(":")[0]
    fun password() = uri.userInfo.split(":")[1]
    fun host() = uri.host!!
    fun port() = uri.port
    fun database() = uri.path.substring(1)
}