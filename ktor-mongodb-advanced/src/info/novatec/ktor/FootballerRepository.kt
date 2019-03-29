package info.novatec.ktor

import org.bson.types.ObjectId
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.eq
import org.litote.kmongo.reactivestreams.KMongo


class FootballerRepository {

    private val mongoClient = KMongo.createClient().coroutine
    private val database = mongoClient.getDatabase("footballmanager")
    private val collection = database.getCollection<Footballer>()

    suspend fun find(): List<Footballer> {
        return collection.find().toList()
    }

    suspend fun find(_id: String): Footballer? {
        return collection.findOne(Footballer::id eq _id)
    }

    suspend fun insert(footballer: Footballer): Footballer {
        footballer.id = ObjectId().toString()
        collection.insertOne(footballer)
        return footballer
    }

    suspend fun delete(_id: String) {
        collection.deleteOne(Footballer::id eq _id)
    }
}
