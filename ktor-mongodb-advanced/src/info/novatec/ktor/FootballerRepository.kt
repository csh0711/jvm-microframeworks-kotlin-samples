package info.novatec.ktor

import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo


class FootballerRepository {

    val mongoClient = KMongo.createClient().coroutine


//    fun find(): List<Footballer> {
//        return getCollection().find().toList()
//    }
//
//    fun find(_id: String): Footballer? {
//        return getCollection().find(eq("_id", ObjectId(_id))).first()
//    }
//
//    fun insert(footballer: Footballer): Footballer {
//        footballer.id = ObjectId()
//        getCollection().insertOne(footballer)
//        return footballer
//    }
//
//    fun delete(_id: String) {
//        getCollection().deleteOne(Filters.eq("_id", ObjectId(_id)))
//    }
//
//    private fun getCollection(): MongoCollection<Footballer> {
//        val codecRegistry = fromRegistries(
//            MongoClient.getDefaultCodecRegistry(),
//            fromProviders(PojoCodecProvider.builder().automatic(true).build())
//        )
//        return mongoClient
//            .getDatabase("footballmanager")
//            .withCodecRegistry(codecRegistry)
//            .getCollection("footballer", Footballer::class.java)
//    }
}
