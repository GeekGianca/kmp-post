package co.com.gcuello.kmp.post.data.mapper

import co.com.gcuello.kmp.post.data.remote.PostDto
import co.com.gcuello.kmp.post.database.entity.CommentEntity
import co.com.gcuello.kmp.post.database.entity.PostEntity
import co.com.gcuello.kmp.post.database.entity.PostWithCommentCount
import co.com.gcuello.kmp.post.models.CommentModel
import co.com.gcuello.kmp.post.models.PostModel
import co.com.gcuello.kmp.post.ui.uiModels.CommentUi
import co.com.gcuello.kmp.post.ui.uiModels.PostUi
import co.com.gcuello.kmp.post.utils.currentTimeMillis

fun PostDto.toEntity() = PostEntity(
    id = id,
    title = title,
    body = body,
    userId = userId,
    lastSync = currentTimeMillis()
)

fun PostEntity.toModel() = PostModel(
    id = id,
    title = title,
    body = body,
    userId = userId
)

fun PostWithCommentCount.toModel() = PostModel(
    id = id,
    title = title,
    body = body,
    userId = userId,
    commentCount = commentCount
)

fun PostModel.toUi() = PostUi(
    id = id,
    title = title,
    body = body,
    commentCount = commentCount
)

fun CommentEntity.toModel() = CommentModel(
    id = id,
    postId = postId,
    name = name,
    email = email,
    body = body,
    lastSync = lastSync
)

fun CommentModel.toUi() = CommentUi(
    id = id.toLong(),
    postId = postId,
    name = name,
    email = email,
    body = body
)
