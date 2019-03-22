package info.novatec

import com.fasterxml.jackson.annotation.JsonIgnore
import org.bson.codecs.pojo.annotations.BsonCreator
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.codecs.pojo.annotations.BsonProperty
import org.bson.types.ObjectId

data class Footballer @BsonCreator constructor(
        @JsonIgnore @BsonId var id: ObjectId?,
        @param:BsonProperty("firstName") val firstName: String?,
        @param:BsonProperty("lastName") val lastName: String?,
        @param:BsonProperty("position") val position: String?
) {
    val _id: String
        get() = id.toString()
}
