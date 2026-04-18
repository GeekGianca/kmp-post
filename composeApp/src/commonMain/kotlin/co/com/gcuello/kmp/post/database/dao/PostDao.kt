package co.com.gcuello.kmp.post.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import co.com.gcuello.kmp.post.database.entity.PostEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {
    @Upsert
    suspend fun upsert(post: PostEntity)

    @Delete
    suspend fun delete(post: PostEntity)

    @Query("SELECT * FROM posts")
    fun findAllPost(): Flow<List<PostEntity>>

    @Query("SELECT * FROM posts WHERE id = :id OR title = :title")
    fun findAllPostByNameOrId(id: Int, title: String): Flow<List<PostEntity>>
}