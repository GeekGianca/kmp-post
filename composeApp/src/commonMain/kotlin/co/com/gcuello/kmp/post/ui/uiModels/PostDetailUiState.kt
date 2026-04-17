package co.com.gcuello.kmp.post.ui.uiModels

sealed interface PostDetailUiState {
    data object Loading : PostDetailUiState
    data class Content(val post: PostUi, val comments: List<CommentUi>) : PostDetailUiState
    data class Error(val message: String) : PostDetailUiState
}