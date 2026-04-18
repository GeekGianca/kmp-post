package co.com.gcuello.kmp.post.domain.usecase

import co.com.gcuello.kmp.post.data.repository.PostRepository
import co.com.gcuello.kmp.post.models.PostModel
import kotlinx.coroutines.flow.Flow

class ObservePostsUseCase(private val repository: PostRepository) {
    operator fun invoke(): Flow<List<PostModel>> = repository.observePosts()
}
