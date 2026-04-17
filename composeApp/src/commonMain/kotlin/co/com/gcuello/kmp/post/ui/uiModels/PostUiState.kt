package co.com.gcuello.kmp.post.ui.uiModels

sealed interface PostsUiState {
    data object Loading : PostsUiState
    data class Content(val items: List<PostUi>) : PostsUiState
    data class Error(val message: String) : PostsUiState
}