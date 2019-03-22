package info.novatec.ktor

import org.bson.codecs.pojo.annotations.BsonId

data class Footballer(
    @BsonId val id: String,
    val firstName: String?,
    val lastName: String?,
    val position: String?
)