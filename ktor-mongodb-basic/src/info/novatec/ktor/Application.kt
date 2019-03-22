package info.novatec.ktor

import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.http.HttpStatusCode
import io.ktor.jackson.jackson
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.jetty.Jetty

fun main() {

    val repository = FootballerRepository()

    val server = embeddedServer(Jetty, port = 8080) {
        routing {
            install(ContentNegotiation) {
                jackson { }
            }
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
    }
    server.start(wait = true)
}
