package co.com.gcuello.kmp.post.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import co.com.gcuello.kmp.post.database.entity.CommentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CommentDao {
    @Upsert
    suspend fun upsert(comment: CommentEntity)

    @Delete
    suspend fun delete(comment: CommentEntity)

    @Query("SELECT * FROM comments WHERE post_id = :postId ORDER BY id DESC")
    fun findByPostId(postId: Int): Flow<List<CommentEntity>>

    @Query("SELECT * FROM comments")
    fun findAllComments(): Flow<List<CommentEntity>>
}
