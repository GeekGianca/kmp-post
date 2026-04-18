package co.com.gcuello.kmp.post.data.repository

import co.com.gcuello.kmp.post.data.mapper.toModel
import co.com.gcuello.kmp.post.database.dao.CommentDao
import co.com.gcuello.kmp.post.database.entity.CommentEntity
import co.com.gcuello.kmp.post.models.CommentModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import co.com.gcuello.kmp.post.utils.currentTimeMillis

class CommentRepositoryImpl(private val commentDao: CommentDao) : CommentRepository {

    override fun observeByPostId(postId: Int): Flow<List<CommentModel>> =
        commentDao.findByPostId(postId).map { it.map { entity -> entity.toModel() } }

    override suspend fun add(postId: Int, name: String, email: String, body: String) {
        commentDao.upsert(
            CommentEntity(
                id = 0,
                postId = postId,
                name = name,
                email = email,
                body = body,
                lastSync = currentTimeMillis()
            )
        )
    }
}
