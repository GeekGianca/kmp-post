package co.com.gcuello.kmp.post.models

data class PostModel(
    val id: Int,
    val title: String,
    val body: String,
    val userId: Int,
    val commentCount: Int = 0
)
