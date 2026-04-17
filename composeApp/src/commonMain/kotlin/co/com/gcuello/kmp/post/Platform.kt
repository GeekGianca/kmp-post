package co.com.gcuello.kmp.post

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform