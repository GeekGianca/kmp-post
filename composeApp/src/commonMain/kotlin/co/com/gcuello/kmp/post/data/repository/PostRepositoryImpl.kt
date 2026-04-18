package co.com.gcuello.kmp.post.data.repository

import co.com.gcuello.kmp.post.data.mapper.toEntity
import co.com.gcuello.kmp.post.data.mapper.toModel
import co.com.gcuello.kmp.post.data.remote.PostApiService
import co.com.gcuello.kmp.post.database.dao.PostDao
import co.com.gcuello.kmp.post.database.dao.PostWithCommentCountDao
import co.com.gcuello.kmp.post.models.PostModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PostRepositoryImpl(
    private val apiService: PostApiService,
    private val postDao: PostDao,
    private val postWithCommentCountDao: PostWithCommentCountDao
) : PostRepository {

    override fun observePosts(): Flow<List<PostModel>> =
        postWithCommentCountDao.observeAll().map { rows -> rows.map { it.toModel() } }

    override suspend fun sync() {
        apiService.getPosts()
            .map { it.toEntity() }
            .forEach { postDao.upsert(it) }
    }
}
