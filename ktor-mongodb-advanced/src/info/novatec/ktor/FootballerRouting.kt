package info.novatec.ktor

import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route

val repository = FootballerRepository()

fun Route.footballerRoutes() {
    route("/footballers") {
        get {
            call.respond(repository.find())
        }
    }
}