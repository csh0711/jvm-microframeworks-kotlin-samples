package info.novatec.ktor

import io.ktor.application.*
import io.ktor.routing.*
import com.fasterxml.jackson.databind.*
import io.ktor.jackson.*
import io.ktor.features.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
//    install(Locations) {
//    }

    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    routing {
        footballerRoutes()


//        get<Footballer> {
//            call.respondText("Location: name=${it.id}, firstName=${it.firstName}, lastName=${it.lastName}, position=${it.lastName}")
//        }
    }
}

//@Location("/footballers/{id}")
//data class Footballer(val id: String, val firstName: String, val lastName: String, val position: String)

// TODO
// - Location
// - KMongo
// - Tests
