package info.novatec.spring.fu

import org.springframework.http.MediaType
import org.springframework.web.reactive.function.BodyExtractors
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono

@Suppress("UNUSED_PARAMETER")
class FootballerHandler(
    private val repository: FootballerRepository
) {

    fun findAll(request: ServerRequest) = ServerResponse
        .ok()
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .body(repository.find())

    fun findOne(request: ServerRequest) = ServerResponse
        .ok()
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .body(repository.find(request.pathVariables()["_id"]!!))

    fun insert(request: ServerRequest): Mono<ServerResponse> {
        val insertedFootballer = repository.insert(
            request.body(
                BodyExtractors.toMono(Footballer::class.java)
            )
        )
        return ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .body(insertedFootballer)
    }

    fun delete(request: ServerRequest): Mono<ServerResponse> {
        repository.delete(request.pathVariables()["_id"]!!)
        return ServerResponse.noContent().build()
    }
}
