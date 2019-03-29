import org.springframework.http.MediaType
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

class FootballerHandler {

    fun find(request: ServerRequest) = ServerResponse
        .ok()
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .body(BodyInserters.fromObject("Hello ${request.queryParam("name").get()}"))
}
