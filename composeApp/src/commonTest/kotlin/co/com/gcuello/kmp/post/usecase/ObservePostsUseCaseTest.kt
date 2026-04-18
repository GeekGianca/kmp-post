package co.com.gcuello.kmp.post.usecase

import co.com.gcuello.kmp.post.domain.usecase.ObservePostsUseCase
import co.com.gcuello.kmp.post.fakes.FakePostRepository
import co.com.gcuello.kmp.post.models.PostModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ObservePostsUseCaseTest {

    private fun postModel(id: Int, title: String = "T$id") =
        PostModel(id = id, title = title, body = "Body", userId = 1)

    @Test
    fun `emits the list returned by the repository`() = runTest {
        val posts = listOf(postModel(1), postModel(2), postModel(3))
        val useCase = ObservePostsUseCase(FakePostRepository(posts = posts))

        val result = useCase().first()

        assertEquals(3, result.size)
        assertEquals(1, result[0].id)
        assertEquals(3, result[2].id)
    }

    @Test
    fun `emits empty list when repository has no posts`() = runTest {
        val useCase = ObservePostsUseCase(FakePostRepository())

        val result = useCase().first()

        assertEquals(emptyList(), result)
    }
}
