package co.com.gcuello.kmp.post.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class PostApiService(private val client: HttpClient) {

    suspend fun getPosts(): List<PostDto> =
        client.get("https://jsonplaceholder.typicode.com/posts").body()
}
