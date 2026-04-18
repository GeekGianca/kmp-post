package co.com.gcuello.kmp.post

import androidx.compose.runtime.*
import co.com.gcuello.kmp.post.ui.PostDetailScreen
import co.com.gcuello.kmp.post.ui.PostDetailViewModel
import co.com.gcuello.kmp.post.ui.PostsScreen
import co.com.gcuello.kmp.post.ui.PostsViewModel
import co.com.gcuello.kmp.post.ui.uiModels.PostDetailUiState
import co.com.gcuello.kmp.post.ui.uiModels.PostsUiState
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun UiDemo() {
    val postsVm: PostsViewModel = koinViewModel()
    val uiState by postsVm.uiState.collectAsState()
    val query by postsVm.query.collectAsState()
    val searchMode by postsVm.searchMode.collectAsState()

    var selectedPostId by remember { mutableStateOf<Int?>(null) }

    if (selectedPostId == null) {
        PostsScreen(
            uiState = uiState,
            query = query,
            searchMode = searchMode,
            onQueryChange = postsVm::onQueryChange,
            onSearchModeChange = postsVm::onSearchModeChange,
            onPostClick = { selectedPostId = it },
            onRetry = postsVm::sync
        )
    } else {
        val postId = selectedPostId!!
        val detailVm: PostDetailViewModel = koinViewModel(parameters = { parametersOf(postId) })
        val comments by detailVm.comments.collectAsState()

        val selectedPost = (uiState as? PostsUiState.Content)?.items?.find { it.id == postId }

        PostDetailScreen(
            uiState = if (selectedPost != null)
                PostDetailUiState.Content(selectedPost, comments)
            else
                PostDetailUiState.Loading,
            onBack = { selectedPostId = null },
            onAddComment = detailVm::addComment
        )
    }
}
