package co.com.gcuello.kmp.post.domain.usecase

import co.com.gcuello.kmp.post.data.repository.CommentRepository
import co.com.gcuello.kmp.post.models.CommentModel
import kotlinx.coroutines.flow.Flow

class ObserveCommentsUseCase(private val repository: CommentRepository) {
    operator fun invoke(postId: Int): Flow<List<CommentModel>> =
        repository.observeByPostId(postId)
}
