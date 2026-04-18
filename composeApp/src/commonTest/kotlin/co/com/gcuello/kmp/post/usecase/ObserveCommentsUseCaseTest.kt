package co.com.gcuello.kmp.post.usecase

import co.com.gcuello.kmp.post.domain.usecase.ObserveCommentsUseCase
import co.com.gcuello.kmp.post.fakes.FakeCommentRepository
import co.com.gcuello.kmp.post.models.CommentModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ObserveCommentsUseCaseTest {

    private fun comment(id: Int, postId: Int) = CommentModel(
        id = id, postId = postId, name = "User$id",
        email = "u$id@test.com", body = "Comment $id", lastSync = 0L
    )

    @Test
    fun `returns only comments for the requested postId`() = runTest {
        val repo = FakeCommentRepository(
            commentsByPostId = mapOf(
                1 to listOf(comment(1, 1), comment(2, 1)),
                2 to listOf(comment(3, 2))
            )
        )
        val useCase = ObserveCommentsUseCase(repo)

        val result = useCase(postId = 1).first()

        assertEquals(2, result.size)
        result.forEach { assertEquals(1, it.postId) }
    }

    @Test
    fun `returns empty list for a postId with no comments`() = runTest {
        val useCase = ObserveCommentsUseCase(FakeCommentRepository())

        val result = useCase(postId = 99).first()

        assertEquals(emptyList(), result)
    }
}
