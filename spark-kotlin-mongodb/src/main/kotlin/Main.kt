import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import info.novatec.javalin.Footballer
import info.novatec.javalin.FootballerRepository
import spark.Spark.*


fun main() {

    val repository = FootballerRepository()

    path("/footballers") {
        port(8080)

        after("/*") {req, res ->  res.type("application/json")}

        get("/") { req, res ->
            res.type("application/json")
            jacksonObjectMapper().writeValueAsString(repository.find())
        }

        get("/:_id") { req, res ->
            val footballer = repository.find(req.params("_id"))
            footballer?.let {
                jacksonObjectMapper().writeValueAsString(footballer)
            } ?: res.status(404)
        }

        post("/") { req, res ->
            val footballer = jacksonObjectMapper().readValue(req.body(), Footballer::class.java)
            val insertedFootballer = repository.insert(footballer)
            jacksonObjectMapper().writeValueAsString(insertedFootballer).also {
                res.status(201)
            }
        }

        delete("/:_id") { req, res ->
            repository.delete(req.params("_id"))
            res.status(204)
        }
    }
}