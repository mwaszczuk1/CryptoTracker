package pl.mwaszczuk.dashboard.sortby

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SortByBottomSheet() {
    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Divider(
            modifier = Modifier
                .requiredWidth(32.dp)
                .padding(bottom = 24.dp),
            color = MaterialTheme.colors.background,
            thickness = 4.dp
        )
    }
}