package co.com.gcuello.kmp.post.utils

import platform.Foundation.NSDate

actual fun currentTimeMillis(): Long =
    (NSDate().timeIntervalSince1970 * 1000).toLong()
