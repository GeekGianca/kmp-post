package co.com.gcuello.kmp.post.di

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import co.com.gcuello.kmp.post.database.DatabasePost
import io.ktor.client.engine.cio.CIO
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single {
        val ctx = androidContext()
        Room.databaseBuilder<DatabasePost>(
            context = ctx,
            name = ctx.getDatabasePath("post.db").absolutePath
        ).fallbackToDestructiveMigration(true)
            .setDriver(BundledSQLiteDriver())
            .build()
    }
    single { createHttpClient(CIO.create()) }
}
