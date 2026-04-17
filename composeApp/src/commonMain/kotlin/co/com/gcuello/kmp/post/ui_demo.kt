package co.com.gcuello.kmp.post

import androidx.compose.runtime.*
import co.com.gcuello.kmp.post.models.FakeData
import co.com.gcuello.kmp.post.ui.PostDetailScreen
import co.com.gcuello.kmp.post.ui.PostsScreen
import co.com.gcuello.kmp.post.ui.uiModels.PostDetailUiState
import co.com.gcuello.kmp.post.ui.uiModels.PostSearchMode
import co.com.gcuello.kmp.post.ui.uiModels.PostsUiState

@Composable
fun UiDemo() {
    var selectedPostId by remember { mutableStateOf<Int?>(null) }

    if (selectedPostId == null) {
        var query by remember { mutableStateOf("") }
        var mode by remember { mutableStateOf(PostSearchMode.BY_TITLE) }

        PostsScreen(
            uiState = PostsUiState.Content(FakeData.posts(30)),
            query = query,
            searchMode = mode,
            onQueryChange = { query = it },
            onSearchModeChange = { mode = it },
            onPostClick = { selectedPostId = it },
            onRetry = {}
        )
    } else {
        val post = FakeData.posts(1).first().copy(id = selectedPostId!!)
        val comments = FakeData.comments(postId = selectedPostId!!, count = 14)

        PostDetailScreen(
            uiState = PostDetailUiState.Content(post, comments),
            onBack = { selectedPostId = null },
            onAddComment = { _, _, _ -> }
        )
    }
}