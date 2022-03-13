package pl.mwaszczuk.dashboard.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import pl.mwaszczuk.dashboard.R
import pl.mwaszczuk.dashboard.model.Cryptocurrency
import pl.mwaszczuk.dashboard.model.Trend
import pl.mwaszczuk.design.defaults.CardDefaults
import pl.mwaszczuk.design.theme.CryptoTrackerTheme
import pl.mwaszczuk.design.theme.Green
import pl.mwaszczuk.design.theme.Red
import pl.mwaszczuk.domain.model.Currency
import pl.mwaszczuk.domain.model.PercentChange

@Composable
fun CryptocurrencyItem(
    modifier: Modifier = Modifier,
    item: Cryptocurrency
) {
    Card(
        modifier = modifier.fillMaxWidth(),
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
                Row {
                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.h2
                    )
                    Icon(
                        modifier = Modifier
                            .padding(start = 8.dp),
                        painter = painterResource(item.trend.iconRes),
                        contentDescription = "trendIcon",
                        tint = item.trend.iconColor
                    )
                }
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
                        .align(Alignment.End)
                        .padding(bottom = 8.dp),
                    text = stringResource(item.price.currencyStringRes, item.price.amount.toString()),
                    style = MaterialTheme.typography.h2
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(bottom = 2.dp),
                    text = stringResource(R.string.daily_percentage_value, item.percentChange24h.value),
                    style = MaterialTheme.typography.body2,
                    color = if (item.percentChange24h.isPositive) {
                        Green
                    } else {
                        Red
                    }
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.End),
                    text = stringResource(R.string.hourly_percentage_value, item.percentChange1h.value),
                    style = MaterialTheme.typography.body2,
                    color = if (item.percentChange1h.isPositive) {
                        Green
                    } else {
                        Red
                    }
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
                Currency(R.string.currency_dollar_value, 38715.15),
                Currency(R.string.currency_dollar_value, 35353.23),
                PercentChange("-1.21%", false),
                PercentChange("-0.17%", false),
                Trend.Flat
            ),
        )
    }
}
