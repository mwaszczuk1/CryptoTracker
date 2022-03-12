package pl.mwaszczuk.dashboard.components

import androidx.compose.foundation.Image
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
import coil.compose.rememberImagePainter
import pl.mwaszczuk.dashboard.model.Cryptocurrency
import pl.mwaszczuk.design.defaults.CardDefaults
import pl.mwaszczuk.design.theme.CryptoTrackerTheme

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
            Image(
                modifier = Modifier
                    .requiredSize(36.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(MaterialTheme.colors.primary),
                painter = rememberImagePainter(item.iconUrl),
                contentDescription = "cryptoIcon"
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
                    text = item.price,
                    style = MaterialTheme.typography.h2
                )
                Text(
                    modifier = Modifier
                        .padding(bottom = 2.dp),
                    text = item.percentChange24h,
                    style = MaterialTheme.typography.body2
                )
                Text(
                    text = item.percentChange1h,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

@Preview
@Composable
fun CryptocurrencyItemPreview() {
    CryptoTrackerTheme {
        CryptocurrencyItem(
            item = Cryptocurrency(
                "1",
                "Bitcoin",
                "BTC",
                "",
                "38715.15$",
                "35353.23$",
                "-1.21%",
                "-0.17%"
            ),
        )
    }
}
