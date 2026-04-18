package co.com.gcuello.kmp.post.di

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import co.com.gcuello.kmp.post.database.DatabasePost
import io.ktor.client.engine.cio.CIO
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.dsl.module
import java.io.File

actual val platformModule: Module = module {
    single {
        val dbFile = File(System.getProperty("user.home"), ".kmppost/post.db")
            .also { it.parentFile?.mkdirs() }
        Room.databaseBuilder<DatabasePost>(name = dbFile.absolutePath)
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .fallbackToDestructiveMigration(
                dropAllTables = true
            )
            .build()
    }
    single { createHttpClient(CIO.create()) }
}
