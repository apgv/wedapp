package codes.foobar.wedapp

import com.google.gson.Gson
import spark.Spark.*

fun main(args: Array<String>) {
    port(System.getenv("PORT").toInt())
    staticFiles.location("/frontend")

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

    get("/", { _, _ ->
        Gson().toJson("hello")
    })
}