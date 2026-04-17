package co.com.gcuello.kmp.post.ui

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopApplicationBar(
    title: String,
    actions: @Composable () -> Unit = {}
) {
    TopAppBar(
        title = { Text(title) },
        actions = { actions() }
    )
}