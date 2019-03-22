import info.novatec.javalin.Footballer
import info.novatec.javalin.FootballerRepository
import org.http4k.core.Body
import org.http4k.core.Method.DELETE
import org.http4k.core.Method.GET
import org.http4k.core.Method.POST
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.CREATED
import org.http4k.core.Status.Companion.NOT_FOUND
import org.http4k.core.Status.Companion.NO_CONTENT
import org.http4k.core.Status.Companion.OK
import org.http4k.core.with
import org.http4k.format.Jackson.auto
import org.http4k.lens.Path
import org.http4k.lens.string
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.Jetty
import org.http4k.server.asServer

fun main() {

    val repository = FootballerRepository()

    val idFromPath = Path.string().of("_id")
    val footballerLens = Body.auto<Footballer>().toLens()
    val footballerListLens = Body.auto<List<Footballer>>().toLens()

    routes(
        "footballers" bind GET to {
            Response(OK).with(footballerListLens.of(repository.find()))
        },
        "footballers/{_id}" bind GET to { request: Request ->
            repository.find(idFromPath(request))?.let {
                Response(OK).with(footballerLens.of(it))
            } ?: Response(NOT_FOUND)
        },
        "footballers" bind POST to { request: Request ->
            repository.insert(footballerLens.extract(request)).let {
                Response(CREATED).with(footballerLens.of(it))
            }
        },
        "footballers/{_id}" bind DELETE to { request: Request ->
            repository.delete(idFromPath(request))
            Response(NO_CONTENT)
        }
    ).asServer(Jetty(8080)).start()
}
