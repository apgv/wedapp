package codes.foobar.wedapp.auth

import codes.foobar.wedapp.helper.JwtHelper
import com.auth0.jwt.interfaces.DecodedJWT

data class JwtUser(
        val subject: String,
        val email: String
)

fun userFromJWT(decodedJWT: DecodedJWT) =
        JwtUser(
                subject = decodedJWT.subject,
                email = JwtHelper.email(decodedJWT)
        )