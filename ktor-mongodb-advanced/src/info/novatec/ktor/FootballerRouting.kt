package info.novatec.ktor

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*

private val repository = FootballerRepository()

fun Route.footballerRoutes() {
    route("/footballers") {
        get {
            call.respond(repository.find())
        }
        get("/{_id}") {
            repository.find(call.parameters["_id"]!!)?.let {
                call.respond(it)
            } ?: call.respond(HttpStatusCode.NotFound)
        }
        post {
            val footballer = call.receive<Footballer>()
            repository.insert(footballer).let {
                call.respond(HttpStatusCode.Created, it)
            }
        }
        delete("/{_id}") {
            repository.delete(call.parameters["_id"]!!)
            call.respond(HttpStatusCode.NoContent)
        }
    }
}
