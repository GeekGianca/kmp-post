package co.com.gcuello.kmp.post.usecase

import co.com.gcuello.kmp.post.domain.usecase.SyncPostsUseCase
import co.com.gcuello.kmp.post.fakes.FakePostRepository
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class SyncPostsUseCaseTest {

    @Test
    fun `invokes repository sync exactly once`() = runTest {
        val repo = FakePostRepository()
        SyncPostsUseCase(repo)()
        assertEquals(1, repo.syncCallCount)
    }

    @Test
    fun `propagates exceptions thrown by the repository`() = runTest {
        val error = RuntimeException("sin red")
        val repo = FakePostRepository(syncError = error)

        val thrown = assertFailsWith<RuntimeException> {
            SyncPostsUseCase(repo)()
        }
        assertEquals("sin red", thrown.message)
    }
}
