package co.com.gcuello.kmp.post.di

import co.com.gcuello.kmp.post.data.remote.PostApiService
import co.com.gcuello.kmp.post.data.repository.CommentRepository
import co.com.gcuello.kmp.post.data.repository.CommentRepositoryImpl
import co.com.gcuello.kmp.post.data.repository.PostRepository
import co.com.gcuello.kmp.post.data.repository.PostRepositoryImpl
import co.com.gcuello.kmp.post.database.DatabasePost
import co.com.gcuello.kmp.post.domain.usecase.AddCommentUseCase
import co.com.gcuello.kmp.post.domain.usecase.ObserveCommentsUseCase
import co.com.gcuello.kmp.post.domain.usecase.ObservePostsUseCase
import co.com.gcuello.kmp.post.domain.usecase.SyncPostsUseCase
import co.com.gcuello.kmp.post.ui.PostDetailViewModel
import co.com.gcuello.kmp.post.ui.PostsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val databaseModule = module {
    single { get<DatabasePost>().postDao() }
    single { get<DatabasePost>().commentDao() }
    single { get<DatabasePost>().postWithCommentCountDao() }
}

val dataModule = module {
    single { PostApiService(get()) }
    single<PostRepository> { PostRepositoryImpl(get(), get(), get()) }
    single<CommentRepository> { CommentRepositoryImpl(get()) }
}

val domainModule = module {
    factory { ObservePostsUseCase(get()) }
    factory { SyncPostsUseCase(get()) }
    factory { ObserveCommentsUseCase(get()) }
    factory { AddCommentUseCase(get()) }
}

val viewModelModule = module {
    viewModelOf(::PostsViewModel)
    viewModel { params -> PostDetailViewModel(params.get(), get(), get()) }
}

val appModules = listOf(databaseModule, dataModule, domainModule, viewModelModule, platformModule)
