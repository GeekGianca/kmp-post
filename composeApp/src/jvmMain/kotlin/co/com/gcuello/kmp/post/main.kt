package co.com.gcuello.kmp.post

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import co.com.gcuello.kmp.post.di.appModules
import co.com.gcuello.kmp.post.ui.AppTheme
import org.koin.core.context.startKoin

fun main() {
    startKoin { modules(appModules) }
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Kmppost",
        ) {
            AppTheme { UiDemo() }
        }
    }
}
