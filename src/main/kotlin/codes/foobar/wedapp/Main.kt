package codes.foobar.wedapp

import com.google.gson.Gson
import spark.Spark.get
import spark.Spark.port

fun main(args: Array<String>) {
    port(System.getenv("PORT").toInt())

    get("/", { _, _ ->
        Gson().toJson("hello")
    })
}