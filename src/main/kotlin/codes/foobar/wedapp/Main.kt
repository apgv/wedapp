package codes.foobar.wedapp

import codes.foobar.wedapp.accommodation.Accommodation
import codes.foobar.wedapp.accommodation.AccommodationRegistration
import codes.foobar.wedapp.accommodation.AccommodationRepository
import codes.foobar.wedapp.auth.userFromJWT
import codes.foobar.wedapp.config.HerokuPostgresConfig
import codes.foobar.wedapp.contact.Contact
import codes.foobar.wedapp.contact.ContactRegistration
import codes.foobar.wedapp.contact.ContactRepository
import codes.foobar.wedapp.direction.Direction
import codes.foobar.wedapp.direction.DirectionsRepository
import codes.foobar.wedapp.gift.Gift
import codes.foobar.wedapp.gift.GiftRepository
import codes.foobar.wedapp.guest.GuestRegistration
import codes.foobar.wedapp.guest.GuestRepository
import codes.foobar.wedapp.helper.DbHelper
import codes.foobar.wedapp.helper.JsonHelper
import codes.foobar.wedapp.helper.JwtHelper
import codes.foobar.wedapp.index.IndexPage
import codes.foobar.wedapp.index.IndexPageRepository
import codes.foobar.wedapp.user.Role
import codes.foobar.wedapp.user.Subject
import codes.foobar.wedapp.user.UserRepository
import codes.foobar.wedapp.user.toSubject
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.interfaces.DecodedJWT
import com.squareup.moshi.Types
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.flywaydb.core.Flyway
import spark.Request
import spark.Spark.*
import java.net.URI
import java.net.URLEncoder
import javax.sql.DataSource

fun main(args: Array<String>) {
    port(System.getenv("PORT").toInt())
    staticFiles.location("/frontend")

    val logger = logger()

    val dbHelper = DbHelper(HerokuPostgresConfig(URI(System.getenv("DATABASE_URL"))))

    migrateDatabase(dbHelper.dataSource)

    val guestRepository = GuestRepository(dbHelper)
    val giftRepository = GiftRepository(dbHelper)
    val indexPageRepository = IndexPageRepository(dbHelper)
    val directionsRepository = DirectionsRepository(dbHelper)
    val accommodationRepository = AccommodationRepository(dbHelper)
    val contactRepository = ContactRepository(dbHelper)
    val userRepository = UserRepository(dbHelper)

    before("/*", { request, response ->
        val herokuOriginatingProtocol = request.headers("X-Forwarded-Proto")
        val secureProtocol = herokuOriginatingProtocol?.equals("https") ?: true

        if (!secureProtocol) {
            logger.info("Unsecure protocol used")
            val url = request.url()
            val secureUrl = url.replaceRange(0, 4, "https")
            val queryString = request.queryString()
            logger.info("Secure URL $secureUrl query string $queryString")

            when {
                queryString.isNullOrBlank() -> response.redirect(secureUrl)
                else -> response.redirect(secureUrl + "?" + queryString)
            }
        }
    })

    path("api", {
        get("/indexpage", { _, _ ->
            val indexPage = indexPageRepository.find()
            JsonHelper.moshi.adapter(IndexPage::class.java).toJson(indexPage)
        })

        post("/indexpage", { request, response ->
            verifyTokenAndCheckRoles(request, listOf(Role.USER), userRepository)
            val jsonAdapter = JsonHelper.moshi.adapter(IndexPage::class.java)
            val indexPage = jsonAdapter.fromJson(request.body())

            if (indexPage != null) {
                indexPageRepository.save(indexPage)
                response.status(201)
            } else {
                response.status(400)
            }
        })

        post("/guests", { request, response ->
            val parameterizedType = Types.newParameterizedType(List::class.java, GuestRegistration::class.java)
            val jsonAdapter = JsonHelper.moshi.adapter<List<GuestRegistration>>(parameterizedType)
            val guestRegistrations = jsonAdapter.fromJson(request.body()).orEmpty()
            logger.info("Register guests $guestRegistrations")

            when {
                guestRegistrations.isNotEmpty() -> {
                    guestRepository.save(guestRegistrations)
                    response.status(201)
                }
                else -> response.status(400)
            }
        })

        get("/gifts", { _, _ ->
            val parameterizedType = Types.newParameterizedType(List::class.java, Gift::class.java)
            val jsonAdapter = JsonHelper.moshi.adapter<List<Gift>>(parameterizedType)
            val gifts = giftRepository.findAll()
            jsonAdapter.toJson(gifts)
        })

        get("/directions", { _, _ ->
            val parameterizedType = Types.newParameterizedType(List::class.java, Direction::class.java)
            val jsonAdapter = JsonHelper.moshi.adapter<List<Direction>>(parameterizedType)
            val directions = directionsRepository.findAll()
            jsonAdapter.toJson(directions)
        })

        get("/accommodations", { _, _ ->
            val parameterizedType = Types.newParameterizedType(List::class.java, Accommodation::class.java)
            val jsonAdapter = JsonHelper.moshi.adapter<List<Accommodation>>(parameterizedType)
            val accommodations = accommodationRepository.findAll()
            jsonAdapter.toJson(accommodations)
        })

        get("/accommodations/:id", { request, _ ->
            verifyTokenAndCheckRoles(request, listOf(Role.USER), userRepository)
            val id = request.params("id")
            val jsonAdapter = JsonHelper.moshi.adapter(Accommodation::class.java)
            val accommodation = accommodationRepository.findById(id.toInt())
            jsonAdapter.toJson(accommodation)
        })

        post("/accommodations", { request, response ->
            verifyTokenAndCheckRoles(request, listOf(Role.USER), userRepository)
            val jsonAdapter = JsonHelper.moshi.adapter(AccommodationRegistration::class.java)
            val accommodationRegistration = jsonAdapter.fromJson(request.body())
            when {
                accommodationRegistration != null -> {
                    accommodationRepository.save(accommodationRegistration)
                    response.status(201)
                }
                else -> response.status(400)
            }
        })

        put("/accommodations", { request, response ->
            verifyTokenAndCheckRoles(request, listOf(Role.USER), userRepository)
            val jsonAdapter = JsonHelper.moshi.adapter(AccommodationRegistration::class.java)
            val accommodationRegistration = jsonAdapter.fromJson(request.body())
            when {
                accommodationRegistration != null -> {
                    accommodationRepository.update(accommodationRegistration)
                    response.status(204)
                }
                else -> response.status(400)
            }
        })

        get("/contacts", { _, _ ->
            val parameterizedType = Types.newParameterizedType(List::class.java, Contact::class.java)
            val jsonAdapter = JsonHelper.moshi.adapter<List<Contact>>(parameterizedType)
            val contacts = contactRepository.findAll()
            jsonAdapter.toJson(contacts)
        })

        get("/contacts/:id", { request, _ ->
            verifyTokenAndCheckRoles(request, listOf(Role.USER), userRepository)
            val id = request.params("id")
            val jsonAdapter = JsonHelper.moshi.adapter(Contact::class.java)
            val contact = contactRepository.findById(id.toInt())
            jsonAdapter.toJson(contact)
        })

        post("/contacts", { request, response ->
            verifyTokenAndCheckRoles(request, listOf(Role.USER), userRepository)
            val jsonAdapter = JsonHelper.moshi.adapter(ContactRegistration::class.java)
            val contactRegistration = jsonAdapter.fromJson(request.body())
            when {
                contactRegistration != null -> {
                    contactRepository.save(contactRegistration)
                    response.status(201)
                }
                else -> response.status(400)
            }
        })

        put("/contacts", { request, response ->
            verifyTokenAndCheckRoles(request, listOf(Role.USER), userRepository)
            val jsonAdapter = JsonHelper.moshi.adapter(ContactRegistration::class.java)
            val contactRegistration = jsonAdapter.fromJson(request.body())
            when {
                contactRegistration != null -> {
                    contactRepository.update(contactRegistration)
                    response.status(204)
                }
                else -> response.status(400)
            }
        })

        get("/subjects/:email", { request, _ ->
            verifyTokenAndCheckRoles(request, listOf(Role.ADMIN, Role.USER), userRepository)
            val email = request.params(":email")
            val jsonAdapter = JsonHelper.moshi.adapter(Subject::class.java)
            val user = userRepository.findByEmail(email)
            jsonAdapter.toJson(toSubject(user))
        })

        after("/*", { _, response ->
            response.type("application/json")
        })
    })

    get("/auth0callback", { _, response ->
        response.redirect("/")
    })

    get("/*", { request, response ->
        response.redirect("/?unknown_api_path=${encodeURL(request.uri())}")
    })

    exception(JWTVerificationException::class.java, { exception, _, response ->
        logger.error(exception)
        response.redirect("/?unknown_api_path=${encodeURL("/reauthenticate")}")
    })
}

private fun logger(): Logger = LogManager.getLogger()

fun migrateDatabase(dataSource: DataSource) {
    val flyway = Flyway()
    flyway.dataSource = dataSource
    flyway.migrate()
}

private fun verifyTokenAndCheckRoles(request: Request,
                                     requiredRoles: List<Role>,
                                     userRepository: UserRepository): DecodedJWT {
    val decodedJWT = JwtHelper.verifyAndDecode(request)
    val jwtUser = userFromJWT(decodedJWT)
    val user = userRepository.findByEmail(jwtUser.email)
    val userRoles = user.roles.map { Role.valueOfIgnoreCase(it.name) }

    return when {
        userRoles.intersect(requiredRoles).isNotEmpty() -> decodedJWT
        else -> {
            val exceptionMessage = String.format(
                    "User %s (%s) is missing one of the roles %s, has roles %s",
                    jwtUser.email,
                    jwtUser.subject,
                    requiredRoles,
                    userRoles
            )
            throw IllegalAccessException(exceptionMessage)
        }
    }
}

private fun encodeURL(uri: String) = URLEncoder.encode(uri, "UTF-8")
