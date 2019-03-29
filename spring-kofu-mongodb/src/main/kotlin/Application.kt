import org.springframework.fu.kofu.web.server
import org.springframework.fu.kofu.webApplication

val app = webApplication {

    beans {
        bean<FootballerHandler>()
//        bean<FootballerRepository>()
    }

    server {
        router {
            val footballerHandler = ref<FootballerHandler>()
            GET("/", footballerHandler::find)
        }
    }
}

fun main() {
    app.run()
}
