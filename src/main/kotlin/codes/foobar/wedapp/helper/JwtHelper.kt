package codes.foobar.wedapp.helper

import com.auth0.jwk.UrlJwkProvider
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import spark.Request
import java.security.interfaces.RSAPublicKey

object JwtHelper {
    private const val AUTH0_URL = "https://apgv.eu.auth0.com/"
    private val urlJwkProvider = UrlJwkProvider(AUTH0_URL)

    fun verifyAndDecode(request: Request): DecodedJWT {
        val jwt = request.headers("X-JWT")
        val decodedJWT = JWT.decode(jwt)
        val jwk = urlJwkProvider.get(decodedJWT.keyId)
        val publicKey = jwk.publicKey
        val algorithm = Algorithm.RSA256(publicKey as RSAPublicKey, null)
        val jwtVerifier = JWT.require(algorithm)
                .withIssuer(AUTH0_URL)
                .build()
        return jwtVerifier.verify(jwt)
    }

    fun email(decodedJWT: DecodedJWT): String = decodedJWT.getClaim("email").asString()
}