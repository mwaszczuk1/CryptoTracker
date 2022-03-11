package pl.mwaszczuk.dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.mwaszczuk.dashboard.model.Cryptocurrency
import pl.mwaszczuk.design.defaults.CardDefaults

@Composable
fun CryptocurrencyItem(
    item: Cryptocurrency
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.ELEVATION,
        backgroundColor = MaterialTheme.colors.surface,
        shape = RoundedCornerShape(CardDefaults.CORNER)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .requiredSize(36.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(MaterialTheme.colors.primary)
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.h2
                )
                Text(
                    modifier = Modifier
                        .padding(top = 8.dp),
                    text = item.symbol,
                    style = MaterialTheme.typography.caption
                )
            }

            Column {
                Text(
                    modifier = Modifier
                        .padding(bottom = 8.dp),
                    text = "333",
                    style = MaterialTheme.typography.h2
                )
                Text(
                    modifier = Modifier
                        .padding(bottom = 2.dp),
                    text = "daily",
                    style = MaterialTheme.typography.body2
                )
                Text(
                    text = "weekly",
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

@Preview
@Composable
fun CryptocurrencyItemPreview() {
    CryptocurrencyItem(
        item = Cryptocurrency("id", "Bitcoin", "BTC")
    )
}