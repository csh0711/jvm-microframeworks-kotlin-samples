package info.novatec

import com.mongodb.MongoClient
import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters.eq
import org.bson.codecs.configuration.CodecRegistries.*
import org.bson.codecs.pojo.PojoCodecProvider
import org.bson.types.ObjectId
import javax.inject.Singleton

@Singleton
class FootballerRepository(
        private val mongoClient: MongoClient
) {

    fun find(): List<Footballer> {
        return getCollection().find().toList()
    }

    fun find(_id: String): Footballer? {
        return getCollection().find(eq("_id", ObjectId(_id))).first()
    }

    fun insert(footballer: Footballer): Footballer {
        footballer.id = ObjectId()
        getCollection().insertOne(footballer)
        return footballer
    }

    fun delete(_id: String) {
        getCollection().deleteOne(eq("_id", ObjectId(_id)))
    }

    private fun getCollection(): MongoCollection<Footballer> {
        val codecRegistry = fromRegistries(
                MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build())
        )
        return mongoClient
                .getDatabase("footballmanager")
                .withCodecRegistry(codecRegistry)
                .getCollection("footballer", Footballer::class.java)
    }

}
