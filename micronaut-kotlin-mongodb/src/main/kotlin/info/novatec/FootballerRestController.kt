package info.novatec

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*

@Controller("/footballers")
class FootballerRestController(
        private val repository: FootballerRepository
) {

    @Get("/")
    fun getAll(): HttpResponse<List<Footballer>> {
        return HttpResponse.ok(repository.find())
    }

    @Get("/{_id}")
    fun getOne(_id: String): HttpResponse<Footballer?>? {
        val footballer = repository.find(_id)
        footballer?.let {
            return HttpResponse.ok(repository.find(_id))
        } ?: return HttpResponse.notFound()

    }

    @Post("/")
    fun postOne(@Body footballer: Footballer): HttpResponse<Footballer> {
        val insertedFootballer = repository.insert(footballer)
        return HttpResponse.created(insertedFootballer)
    }

    @Delete("/{_id}")
    fun deleteOne(_id: String): HttpResponse<Footballer> {
        repository.delete(_id)
        return HttpResponse.noContent()
    }
}