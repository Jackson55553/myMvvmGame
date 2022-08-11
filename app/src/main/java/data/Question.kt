package data

import java.sql.CallableStatement

data class Question(
    val statement: String,
    var exercise: String = ""
)
