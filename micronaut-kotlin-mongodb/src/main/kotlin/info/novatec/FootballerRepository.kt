package info.novatec

import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters.eq
import org.bson.UuidRepresentation.JAVA_LEGACY
import org.bson.codecs.UuidCodec
import org.bson.codecs.configuration.CodecRegistries.*
import org.bson.codecs.pojo.PojoCodecProvider
import org.bson.types.ObjectId
import java.util.*
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
            // fromCodecs(UuidCodec(JAVA_LEGACY)), // currently not needed as we use an ObjectId for _id and not an UUID
            MongoClientSettings.getDefaultCodecRegistry(),
            fromProviders(PojoCodecProvider.builder().automatic(true).build())
        )
        return mongoClient
            .getDatabase("footballmanager")
            .withCodecRegistry(codecRegistry)
            .getCollection("footballer", Footballer::class.java)
    }
}
