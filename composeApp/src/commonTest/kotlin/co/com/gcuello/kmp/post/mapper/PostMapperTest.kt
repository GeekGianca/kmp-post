package co.com.gcuello.kmp.post.mapper

import co.com.gcuello.kmp.post.data.mapper.toEntity
import co.com.gcuello.kmp.post.data.mapper.toModel
import co.com.gcuello.kmp.post.data.mapper.toUi
import co.com.gcuello.kmp.post.data.remote.PostDto
import co.com.gcuello.kmp.post.database.entity.CommentEntity
import co.com.gcuello.kmp.post.database.entity.PostEntity
import co.com.gcuello.kmp.post.database.entity.PostWithCommentCount
import co.com.gcuello.kmp.post.models.CommentModel
import co.com.gcuello.kmp.post.models.PostModel
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PostMapperTest {

    // ── PostDto → PostEntity ─────────────────────────────────────────────────

    @Test
    fun `PostDto toEntity preserves id title body and userId`() {
        val dto = PostDto(id = 7, userId = 3, title = "Hola", body = "Mundo")
        val entity = dto.toEntity()
        assertEquals(7, entity.id)
        assertEquals(3, entity.userId)
        assertEquals("Hola", entity.title)
        assertEquals("Mundo", entity.body)
    }

    @Test
    fun `PostDto toEntity sets lastSync to a positive timestamp`() {
        val entity = PostDto(id = 1, userId = 1, title = "T", body = "B").toEntity()
        assertTrue(entity.lastSync > 0, "lastSync debe ser un timestamp positivo")
    }

    // ── PostEntity → PostModel ───────────────────────────────────────────────

    @Test
    fun `PostEntity toModel maps all fields and defaults commentCount to zero`() {
        val entity = PostEntity(id = 2, title = "T", body = "B", userId = 5, lastSync = 1000L)
        val model = entity.toModel()
        assertEquals(2, model.id)
        assertEquals("T", model.title)
        assertEquals("B", model.body)
        assertEquals(5, model.userId)
        assertEquals(0, model.commentCount)
    }

    // ── PostWithCommentCount → PostModel ─────────────────────────────────────

    @Test
    fun `PostWithCommentCount toModel carries commentCount`() {
        val view = PostWithCommentCount(
            id = 10, title = "Vista", body = "Cuerpo",
            userId = 1, lastSync = 500L, commentCount = 42
        )
        val model = view.toModel()
        assertEquals(10, model.id)
        assertEquals(42, model.commentCount)
    }

    // ── PostModel → PostUi ───────────────────────────────────────────────────

    @Test
    fun `PostModel toUi maps id title body and commentCount`() {
        val model = PostModel(id = 3, title = "UI", body = "Test", userId = 9, commentCount = 5)
        val ui = model.toUi()
        assertEquals(3, ui.id)
        assertEquals("UI", ui.title)
        assertEquals("Test", ui.body)
        assertEquals(5, ui.commentCount)
    }

    // ── CommentEntity → CommentModel ─────────────────────────────────────────

    @Test
    fun `CommentEntity toModel maps all fields`() {
        val entity = CommentEntity(
            id = 99, postId = 4, name = "Ana",
            email = "ana@test.com", body = "Buen post", lastSync = 2000L
        )
        val model = entity.toModel()
        assertEquals(99, model.id)
        assertEquals(4, model.postId)
        assertEquals("Ana", model.name)
        assertEquals("ana@test.com", model.email)
        assertEquals("Buen post", model.body)
        assertEquals(2000L, model.lastSync)
    }

    // ── CommentModel → CommentUi ─────────────────────────────────────────────

    @Test
    fun `CommentModel toUi maps id as Long and all string fields`() {
        val model = CommentModel(
            id = 55, postId = 8, name = "Pedro",
            email = "p@test.com", body = "Comentario", lastSync = 0L
        )
        val ui = model.toUi()
        assertEquals(55L, ui.id)
        assertEquals(8, ui.postId)
        assertEquals("Pedro", ui.name)
        assertEquals("p@test.com", ui.email)
        assertEquals("Comentario", ui.body)
    }
}
