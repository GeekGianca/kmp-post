package co.com.gcuello.kmp.post.domain.usecase

import co.com.gcuello.kmp.post.data.repository.CommentRepository

class AddCommentUseCase(private val repository: CommentRepository) {
    suspend operator fun invoke(postId: Int, name: String, email: String, body: String) =
        repository.add(postId, name, email, body)
}
