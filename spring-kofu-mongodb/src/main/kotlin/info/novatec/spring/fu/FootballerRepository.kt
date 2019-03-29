package info.novatec.spring.fu

import org.springframework.data.mongodb.core.ReactiveMongoOperations
import org.springframework.data.mongodb.core.findAll
import org.springframework.data.mongodb.core.findById
import org.springframework.data.mongodb.core.remove
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import reactor.core.publisher.Mono

class FootballerRepository(
    private val mongo: ReactiveMongoOperations
) {

    fun find() = mongo.findAll<Footballer>()

    fun find(_id: String) = mongo.findById<Footballer>(_id)

    fun insert(footballer: Mono<Footballer>) = mongo.save(footballer)

    // TODO: Fix deletion!
    fun delete(_id: String) = mongo.remove(Query.query(Criteria.where("id").`is`(_id)), Footballer::class)
}
