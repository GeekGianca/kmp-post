package co.com.gcuello.kmp.post.models

data class CommentModel(
    val id: Int,
    val postId: Int,
    val name: String,
    val email: String,
    val body: String,
    val lastSync: Long
)
