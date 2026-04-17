package co.com.gcuello.kmp.post.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.com.gcuello.kmp.post.ui.uiModels.PostSearchMode
import co.com.gcuello.kmp.post.ui.uiModels.PostsUiState

@Composable
fun PostsScreen(
    uiState: PostsUiState,
    query: String,
    searchMode: PostSearchMode,
    onQueryChange: (String) -> Unit,
    onSearchModeChange: (PostSearchMode) -> Unit,
    onPostClick: (Int) -> Unit,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier.fillMaxSize()) {
        TopApplicationBar(title = "Publicaciones")

        Column(Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
            PostSearchBar(
                query = query,
                onQueryChange = onQueryChange,
                mode = searchMode,
                onModeChange = onSearchModeChange
            )
        }

        when (uiState) {
            PostsUiState.Loading -> {
                PostsSkeletonList(Modifier.padding(horizontal = 16.dp))
            }
            is PostsUiState.Error -> {
                ErrorState(
                    message = uiState.message,
                    onRetry = onRetry,
                    modifier = Modifier.fillMaxSize()
                )
            }
            is PostsUiState.Content -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(uiState.items, key = { it.id }) { post ->
                        PostCard(
                            post = post,
                            onClick = { onPostClick(post.id) }
                        )
                    }
                }
            }
        }
    }
}