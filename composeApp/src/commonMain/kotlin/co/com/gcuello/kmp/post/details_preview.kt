package co.com.gcuello.kmp.post

import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import co.com.gcuello.kmp.post.models.FakeData
import co.com.gcuello.kmp.post.ui.PostDetailScreen
import co.com.gcuello.kmp.post.ui.uiModels.PostDetailUiState

@Preview(showBackground = true)
@Composable
fun Preview_PostDetail_Content() {
    val post = FakeData.posts(1).first().copy(id = 7)
    val comments = FakeData.comments(postId = post.id, count = 10)

    PostDetailScreen(
        uiState = PostDetailUiState.Content(post = post, comments = comments),
        onBack = {},
        onAddComment = { _, _, _ -> }
    )
}

@Preview(showBackground = true)
@Composable
fun Preview_PostDetail_EmptyComments() {
    val post = FakeData.posts(1).first().copy(id = 3)

    PostDetailScreen(
        uiState = PostDetailUiState.Content(post = post, comments = emptyList()),
        onBack = {},
        onAddComment = { _, _, _ -> }
    )
}