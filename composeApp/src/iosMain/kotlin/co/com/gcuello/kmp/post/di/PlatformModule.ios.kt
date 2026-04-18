package co.com.gcuello.kmp.post.di

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import co.com.gcuello.kmp.post.database.DatabasePost
import io.ktor.client.engine.darwin.Darwin
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.Foundation.NSHomeDirectory

actual val platformModule: Module = module {
    single {
        val dbPath = NSHomeDirectory() + "/Documents/post.db"
        Room.databaseBuilder<DatabasePost>(name = dbPath)
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.Default)
            .fallbackToDestructiveMigration(from = 1)
            .build()
    }
    single { createHttpClient(Darwin.create()) }
}
