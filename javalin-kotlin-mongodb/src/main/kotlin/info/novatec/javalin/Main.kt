package info.novatec.javalin

import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*

fun main() {
    val app = Javalin.create().start(8080)

    val repository = FootballerRepository()

    app.routes {
        path("/footballers") {

            get("/") { ctx ->
                ctx.json(repository.find())
            }

            get("/:_id") { ctx ->
                val footballer = repository.find(ctx.pathParam("_id"))
                footballer?.let {
                    ctx.json(it)
                } ?: ctx.status(404)
            }

            post("/") { ctx ->
                val footballer = ctx.body<Footballer>()
                val insertedFootballer = repository.insert(footballer)
                ctx.json(insertedFootballer)
                ctx.status(201)
            }

            delete("/:_id") { ctx ->
                repository.delete(ctx.pathParam("_id"))
                ctx.status(204)
            }
        }
    }
}
