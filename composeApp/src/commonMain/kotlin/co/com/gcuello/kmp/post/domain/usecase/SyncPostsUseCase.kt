package co.com.gcuello.kmp.post.domain.usecase

import co.com.gcuello.kmp.post.data.repository.PostRepository

class SyncPostsUseCase(private val repository: PostRepository) {
    suspend operator fun invoke() = repository.sync()
}
