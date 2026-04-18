package co.com.gcuello.kmp.post

import android.app.Application
import co.com.gcuello.kmp.post.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KmpApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@KmpApplication)
            modules(appModules)
        }
    }
}
