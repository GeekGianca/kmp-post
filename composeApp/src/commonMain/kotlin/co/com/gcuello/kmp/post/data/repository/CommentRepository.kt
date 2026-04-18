package co.com.gcuello.kmp.post.data.repository

import co.com.gcuello.kmp.post.models.CommentModel
import kotlinx.coroutines.flow.Flow

interface CommentRepository {
    fun observeByPostId(postId: Int): Flow<List<CommentModel>>
    suspend fun add(postId: Int, name: String, email: String, body: String)
}
