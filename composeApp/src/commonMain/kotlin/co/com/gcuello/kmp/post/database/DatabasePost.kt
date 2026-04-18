package co.com.gcuello.kmp.post.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import co.com.gcuello.kmp.post.database.dao.CommentDao
import co.com.gcuello.kmp.post.database.dao.PostDao
import co.com.gcuello.kmp.post.database.dao.PostWithCommentCountDao
import co.com.gcuello.kmp.post.database.entity.CommentEntity
import co.com.gcuello.kmp.post.database.entity.PostEntity
import co.com.gcuello.kmp.post.database.entity.PostWithCommentCount

@Database(
    entities = [PostEntity::class, CommentEntity::class],
    views = [PostWithCommentCount::class],
    version = 2
)
@ConstructedBy(DatabasePostConstructor::class)
abstract class DatabasePost : RoomDatabase() {
    abstract fun postDao(): PostDao
    abstract fun commentDao(): CommentDao
    abstract fun postWithCommentCountDao(): PostWithCommentCountDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object DatabasePostConstructor : RoomDatabaseConstructor<DatabasePost> {
    override fun initialize(): DatabasePost
}
