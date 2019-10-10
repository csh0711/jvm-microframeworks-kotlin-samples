package info.novatec.spring.fu

import org.springframework.boot.WebApplicationType
import org.springframework.fu.kofu.application
import org.springframework.fu.kofu.mongo.reactiveMongodb
import org.springframework.fu.kofu.webflux.webFlux

val app = application(WebApplicationType.REACTIVE)  {

    beans {
        bean<FootballerHandler>()
        bean<FootballerRepository>()
    }

    reactiveMongodb {
        uri = "mongodb://localhost:27017/footballmanager"
    }

    webFlux {
        port = 8080
        codecs {
            string()
            jackson()
        }

        router {
            val footballerHandler = ref<FootballerHandler>()
                GET("/footballers", footballerHandler::findAll)
                GET("/footballers/{_id}", footballerHandler::findOne)
                POST("/footballers", footballerHandler::insert)
                DELETE("/footballers/{_id}", footballerHandler::delete)
        }
    }
}

fun main() {
    app.run()
}
