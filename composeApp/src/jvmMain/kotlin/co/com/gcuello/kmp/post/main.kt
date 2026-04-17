package co.com.gcuello.kmp.post

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import co.com.gcuello.kmp.post.ui.AppTheme

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Kmppost",
    ) {
        AppTheme { UiDemo() }
    }
}