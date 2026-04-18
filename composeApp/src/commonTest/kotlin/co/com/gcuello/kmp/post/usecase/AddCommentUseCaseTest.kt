package co.com.gcuello.kmp.post.usecase

import co.com.gcuello.kmp.post.domain.usecase.AddCommentUseCase
import co.com.gcuello.kmp.post.fakes.FakeCommentRepository
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class AddCommentUseCaseTest {

    @Test
    fun `delegates add to repository with correct fields`() = runTest {
        val repo = FakeCommentRepository()
        val useCase = AddCommentUseCase(repo)

        useCase(postId = 5, name = "Ana", email = "ana@test.com", body = "Gran post")

        assertEquals(1, repo.added.size)
        val added = repo.added.first()
        assertEquals(5, added.postId)
        assertEquals("Ana", added.name)
        assertEquals("ana@test.com", added.email)
        assertEquals("Gran post", added.body)
    }

    @Test
    fun `multiple adds are all recorded`() = runTest {
        val repo = FakeCommentRepository()
        val useCase = AddCommentUseCase(repo)

        useCase(1, "A", "a@x.com", "Primero")
        useCase(1, "B", "b@x.com", "Segundo")

        assertEquals(2, repo.added.size)
    }
}
