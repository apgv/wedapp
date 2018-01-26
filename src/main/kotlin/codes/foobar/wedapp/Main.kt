package codes.foobar.wedapp

import com.google.gson.Gson
import spark.Spark.get

fun main(args: Array<String>) {
    get("/", { _, _ ->
        Gson().toJson("hello")
    })
}