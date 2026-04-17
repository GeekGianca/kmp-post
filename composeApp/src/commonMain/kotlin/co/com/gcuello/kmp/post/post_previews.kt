package co.com.gcuello.kmp.post

import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import co.com.gcuello.kmp.post.models.FakeData
import co.com.gcuello.kmp.post.ui.PostsScreen
import co.com.gcuello.kmp.post.ui.uiModels.PostSearchMode
import co.com.gcuello.kmp.post.ui.uiModels.PostsUiState

@Preview(showBackground = true)
@Composable
fun Preview_PostsScreen_Content() {
    var query by remember { mutableStateOf("") }
    var mode by remember { mutableStateOf(PostSearchMode.BY_TITLE) }

    PostsScreen(
        uiState = PostsUiState.Content(FakeData.posts(18)),
        query = query,
        searchMode = mode,
        onQueryChange = { query = it },
        onSearchModeChange = { mode = it },
        onPostClick = {},
        onRetry = {}
    )
}

@Preview(showBackground = true)
@Composable
fun Preview_PostsScreen_Loading() {
    PostsScreen(
        uiState = PostsUiState.Loading,
        query = "",
        searchMode = PostSearchMode.BY_TITLE,
        onQueryChange = {},
        onSearchModeChange = {},
        onPostClick = {},
        onRetry = {}
    )
}

@Preview(showBackground = true)
@Composable
fun Preview_PostsScreen_Error() {
    PostsScreen(
        uiState = PostsUiState.Error("No se pudo cargar el listado. Verifica tu conexión o intenta más tarde."),
        query = "",
        searchMode = PostSearchMode.BY_TITLE,
        onQueryChange = {},
        onSearchModeChange = {},
        onPostClick = {},
        onRetry = {}
    )
}