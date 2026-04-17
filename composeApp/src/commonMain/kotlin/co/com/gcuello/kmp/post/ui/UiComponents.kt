package co.com.gcuello.kmp.post.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import co.com.gcuello.kmp.post.ui.uiModels.PostSearchMode

@Composable
fun PostSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    mode: PostSearchMode,
    onModeChange: (PostSearchMode) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = query,
            onValueChange = onQueryChange,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            label = { Text(if (mode == PostSearchMode.BY_TITLE) "Buscar por título" else "Buscar por ID") }
        )

        Spacer(Modifier.height(10.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            FilterChip(
                selected = mode == PostSearchMode.BY_TITLE,
                onClick = { onModeChange(PostSearchMode.BY_TITLE) },
                label = { Text("Por título") }
            )
            FilterChip(
                selected = mode == PostSearchMode.BY_ID,
                onClick = { onModeChange(PostSearchMode.BY_ID) },
                label = { Text("Por ID") }
            )
        }
    }
}

@Composable
fun InfoPill(text: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.clip(RoundedCornerShape(999.dp)),
        color = MaterialTheme.colorScheme.secondaryContainer,
        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
        )
    }
}