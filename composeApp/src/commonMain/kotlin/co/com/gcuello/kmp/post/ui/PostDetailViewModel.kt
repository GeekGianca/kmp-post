package co.com.gcuello.kmp.post.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.com.gcuello.kmp.post.data.mapper.toUi
import co.com.gcuello.kmp.post.domain.usecase.AddCommentUseCase
import co.com.gcuello.kmp.post.domain.usecase.ObserveCommentsUseCase
import co.com.gcuello.kmp.post.ui.uiModels.CommentUi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PostDetailViewModel(
    private val postId: Int,
    private val observeComments: ObserveCommentsUseCase,
    private val addComment: AddCommentUseCase
) : ViewModel() {

    val comments: StateFlow<List<CommentUi>> = observeComments(postId)
        .map { list -> list.map { it.toUi() } }
        .catch { emit(emptyList()) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun addComment(name: String, email: String, body: String) {
        viewModelScope.launch {
            addComment(postId, name, email, body)
        }
    }
}
