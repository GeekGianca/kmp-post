package co.com.gcuello.kmp.post.fakes

import co.com.gcuello.kmp.post.data.repository.CommentRepository
import co.com.gcuello.kmp.post.models.CommentModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeCommentRepository(
    private val commentsByPostId: Map<Int, List<CommentModel>> = emptyMap()
) : CommentRepository {

    data class AddedComment(val postId: Int, val name: String, val email: String, val body: String)

    val added = mutableListOf<AddedComment>()

    override fun observeByPostId(postId: Int): Flow<List<CommentModel>> =
        flowOf(commentsByPostId[postId] ?: emptyList())

    override suspend fun add(postId: Int, name: String, email: String, body: String) {
        added += AddedComment(postId, name, email, body)
    }
}
