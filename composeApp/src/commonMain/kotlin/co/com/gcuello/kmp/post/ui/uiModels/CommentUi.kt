package co.com.gcuello.kmp.post.ui.uiModels

import kotlin.time.Clock

data class CommentUi(
    val id: Long,
    val postId: Int,
    val name: String,
    val email: String,
    val body: String,
    val createdAt: Long = Clock.System.now().epochSeconds
)