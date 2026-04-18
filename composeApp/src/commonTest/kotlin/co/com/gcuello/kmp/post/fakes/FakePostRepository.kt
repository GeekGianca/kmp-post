package co.com.gcuello.kmp.post.fakes

import co.com.gcuello.kmp.post.data.repository.PostRepository
import co.com.gcuello.kmp.post.models.PostModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakePostRepository(
    private val posts: List<PostModel> = emptyList(),
    private val syncError: Throwable? = null
) : PostRepository {

    var syncCallCount = 0
        private set

    override fun observePosts(): Flow<List<PostModel>> = flowOf(posts)

    override suspend fun sync() {
        syncCallCount++
        syncError?.let { throw it }
    }
}
