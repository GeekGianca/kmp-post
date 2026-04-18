package co.com.gcuello.kmp.post.data.repository

import co.com.gcuello.kmp.post.models.PostModel
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    fun observePosts(): Flow<List<PostModel>>
    suspend fun sync()
}
