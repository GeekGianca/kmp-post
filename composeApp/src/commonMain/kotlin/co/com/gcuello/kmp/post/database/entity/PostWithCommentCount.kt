package co.com.gcuello.kmp.post.database.entity

import androidx.room.ColumnInfo
import androidx.room.DatabaseView

@DatabaseView(
    viewName = "post_with_comment_count",
    value = """
        SELECT p.id, p.title, p.body, p.user_id, p.last_sync_at,
               COUNT(c.id) AS comment_count
        FROM posts p
        LEFT JOIN comments c ON p.id = c.post_id
        GROUP BY p.id
    """
)
data class PostWithCommentCount(
    val id: Int,
    val title: String,
    val body: String,
    @ColumnInfo(name = "user_id") val userId: Int,
    @ColumnInfo(name = "last_sync_at") val lastSync: Long,
    @ColumnInfo(name = "comment_count") val commentCount: Int
)
