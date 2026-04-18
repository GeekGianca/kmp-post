package co.com.gcuello.kmp.post

import androidx.compose.ui.window.ComposeUIViewController
import co.com.gcuello.kmp.post.di.appModules
import co.com.gcuello.kmp.post.ui.AppTheme
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        startKoin { modules(appModules) }
    }
) { AppTheme { UiDemo() } }
