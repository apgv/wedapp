package codes.foobar.wedapp

import com.google.gson.Gson
import spark.Spark.*

fun main(args: Array<String>) {
    port(System.getenv("PORT").toInt())
    staticFiles.location("/frontend")

    get("/", { _, _ ->
        Gson().toJson("hello")
    })
}