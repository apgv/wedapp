package codes.foobar.wedapp

import codes.foobar.wedapp.config.HerokuPostgresConfig
import codes.foobar.wedapp.guest.GuestRegistration
import codes.foobar.wedapp.guest.GuestRepository
import codes.foobar.wedapp.helper.DbHelper
import codes.foobar.wedapp.helper.JsonHelper
import com.squareup.moshi.Types
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.flywaydb.core.Flyway
import spark.Spark.*
import java.net.URI
import javax.sql.DataSource

fun main(args: Array<String>) {
    port(System.getenv("PORT").toInt())
    staticFiles.location("/frontend")

    val logger = logger()

    val dbHelper = DbHelper(HerokuPostgresConfig(URI(System.getenv("DATABASE_URL"))))

    migrateDatabase(dbHelper.dataSource)

    val guestRepository = GuestRepository(dbHelper)

    before("/*", { request, response ->
        val herokuOriginatingProtocol = request.headers("X-Forwarded-Proto")
        val secureProtocol = herokuOriginatingProtocol?.equals("https") ?: true

        if (!secureProtocol) {
            val url = request.url()
            val secureUrl = url.replaceRange(0, 4, "https")
            val queryString = request.queryString()

            when {
                queryString.isNullOrBlank() -> response.redirect(secureUrl)
                else -> response.redirect(secureUrl + "?" + queryString)
            }
        }
    })

    path("api", {
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
    })

    after("/*", { _, response ->
        response.type("application/json")
    })
}

private fun logger(): Logger = LogManager.getLogger()

fun migrateDatabase(dataSource: DataSource) {
    val flyway = Flyway()
    flyway.dataSource = dataSource
    flyway.migrate()
}
