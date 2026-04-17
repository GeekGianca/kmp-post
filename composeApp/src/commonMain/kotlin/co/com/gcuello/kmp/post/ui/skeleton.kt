package co.com.gcuello.kmp.post.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PostsSkeletonList(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition(label = "shimmer")
    val x by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmerX"
    )

    val brush = Brush.linearGradient(
        colors = listOf(
            Color(0xFFEAEAEA),
            Color(0xFFF5F5F5),
            Color(0xFFEAEAEA)
        ),
        start = Offset(x, 0f),
        end = Offset(x + 250f, 250f)
    )

    Column(modifier = modifier.fillMaxWidth()) {
        repeat(6) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(92.dp)
                    .padding(vertical = 8.dp)
                    .background(brush, RoundedCornerShape(18.dp))
            )
        }
    }
}