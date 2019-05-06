package info.novatec.ktor

import io.ktor.application.*
import io.ktor.routing.*
import com.fasterxml.jackson.databind.*
import io.ktor.jackson.*
import io.ktor.features.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {

    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    routing {
        footballerRoutes()
    }
}
