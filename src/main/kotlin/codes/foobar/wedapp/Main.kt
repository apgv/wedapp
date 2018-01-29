package codes.foobar.wedapp

import codes.foobar.wedapp.config.HerokuPostgresConfig
import codes.foobar.wedapp.guest.GuestRegistration
import codes.foobar.wedapp.guest.GuestRepository
import codes.foobar.wedapp.helper.DbHelper
import codes.foobar.wedapp.helper.JsonHelper
import codes.foobar.wedapp.index.IndexPage
import codes.foobar.wedapp.index.IndexPageRepository
import com.squareup.moshi.Types
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.flywaydb.core.Flyway
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
    val indexPageRepository = IndexPageRepository(dbHelper)

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

        after("/*", { _, response ->
            response.type("application/json")
        })
    })

    get("/*", { request, response ->
        response.redirect("/?unknown_api_path=${encodeURL(request.uri())}")
    })
}

private fun logger(): Logger = LogManager.getLogger()

fun migrateDatabase(dataSource: DataSource) {
    val flyway = Flyway()
    flyway.dataSource = dataSource
    flyway.migrate()
}

private fun encodeURL(uri: String) = URLEncoder.encode(uri, "UTF-8")
