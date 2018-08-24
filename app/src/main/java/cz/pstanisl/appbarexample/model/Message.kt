package cz.pstanisl.appbarexample.model

data class Message(
    val id: Int = 0,
    val from: String,
    val subject: String,
    val message: String,
    val timestamp: String,
    val picture: String,
    var isImportant: Boolean,
    val isRead: Boolean,
    var color: Int = -1
)