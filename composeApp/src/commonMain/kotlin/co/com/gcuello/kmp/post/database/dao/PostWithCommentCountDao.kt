package co.com.gcuello.kmp.post.database.dao

import androidx.room.Dao
import androidx.room.Query
import co.com.gcuello.kmp.post.database.entity.PostWithCommentCount
import kotlinx.coroutines.flow.Flow

@Dao
interface PostWithCommentCountDao {
    @Query("SELECT * FROM post_with_comment_count")
    fun observeAll(): Flow<List<PostWithCommentCount>>
}
