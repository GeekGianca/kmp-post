package co.com.gcuello.kmp.post.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("posts")
data class PostEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "body")
    val body: String,
    @ColumnInfo(name = "user_id")
    val userId: Int,
    @ColumnInfo(name = "last_sync_at")
    val lastSync: Long
)
