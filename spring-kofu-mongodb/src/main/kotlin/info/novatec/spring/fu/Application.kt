package info.novatec.spring.fu

import org.springframework.fu.kofu.mongo.mongodb
import org.springframework.fu.kofu.web.server
import org.springframework.fu.kofu.webApplication

val app = webApplication {

    beans {
        bean<FootballerHandler>()
        bean<FootballerRepository>()
    }

    mongodb {
        uri = "mongodb://localhost:27017/footballmanager"
    }

    server {
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
