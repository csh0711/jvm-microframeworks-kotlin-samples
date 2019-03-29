package info.novatec.spring.fu

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Footballer (
    @Id var _id: String?,
    val firstName: String?,
    val lastName: String?,
    val position: String?
)