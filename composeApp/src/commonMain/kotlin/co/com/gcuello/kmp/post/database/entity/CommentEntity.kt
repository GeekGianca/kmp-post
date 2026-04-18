package co.com.gcuello.kmp.post.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("comments")
data class CommentEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "post_id")
    val postId: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "body")
    val body: String,
    @ColumnInfo(name = "last_sync_at")
    val lastSync: Long
)
