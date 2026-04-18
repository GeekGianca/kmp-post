package co.com.gcuello.kmp.post.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.com.gcuello.kmp.post.data.mapper.toUi
import co.com.gcuello.kmp.post.domain.usecase.ObservePostsUseCase
import co.com.gcuello.kmp.post.domain.usecase.SyncPostsUseCase
import co.com.gcuello.kmp.post.ui.uiModels.PostSearchMode
import co.com.gcuello.kmp.post.ui.uiModels.PostsUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PostsViewModel(
    private val observePosts: ObservePostsUseCase,
    private val syncPosts: SyncPostsUseCase
) : ViewModel() {

    private val _query = MutableStateFlow("")
    private val _searchMode = MutableStateFlow(PostSearchMode.BY_TITLE)
    private val _syncError = MutableStateFlow<String?>(null)

    val query: StateFlow<String> = _query.asStateFlow()
    val searchMode: StateFlow<PostSearchMode> = _searchMode.asStateFlow()

    val uiState: StateFlow<PostsUiState> = combine(
        observePosts(), _query, _searchMode, _syncError
    ) { posts, query, mode, syncError ->
        if (posts.isEmpty() && syncError != null) return@combine PostsUiState.Error(syncError)
        val uiPosts = posts.map { it.toUi() }
        val filtered = if (query.isBlank()) uiPosts
        else when (mode) {
            PostSearchMode.BY_TITLE -> uiPosts.filter { it.title.contains(query, ignoreCase = true) }
            PostSearchMode.BY_ID -> uiPosts.filter { it.id.toString() == query.trim() }
        }
        PostsUiState.Content(filtered) as PostsUiState
    }
        .catch { e -> emit(PostsUiState.Error(e.message ?: "Error desconocido")) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), PostsUiState.Loading)

    init { sync() }

    fun sync() {
        _syncError.value = null
        viewModelScope.launch(Dispatchers.Default) {
            try {
                syncPosts()
            } catch (e: Exception) {
                _syncError.value = e.message ?: "Error de red"
            }
        }
    }

    fun onQueryChange(value: String) { _query.value = value }
    fun onSearchModeChange(mode: PostSearchMode) { _searchMode.value = mode }
}
