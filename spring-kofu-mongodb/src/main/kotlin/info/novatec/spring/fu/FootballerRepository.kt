package info.novatec.spring.fu

import org.springframework.data.mongodb.core.ReactiveMongoOperations
import org.springframework.data.mongodb.core.findAll
import org.springframework.data.mongodb.core.findById
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.data.mongodb.core.query.Query.query
import org.springframework.data.mongodb.core.remove
import reactor.core.publisher.Mono


class FootballerRepository(
    private val mongo: ReactiveMongoOperations
) {

    fun find() = mongo.findAll<Footballer>()

    fun find(_id: String) = mongo.findById<Footballer>(_id)

    fun insert(footballer: Mono<Footballer>) = mongo.save(footballer)

    fun delete(_id: String) =
        mongo.remove(query(where("_id").`is`(_id)), Footballer::class).subscribe()

}
