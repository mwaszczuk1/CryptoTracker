package pl.mwaszczuk.dashboard.model

import pl.mwaszczuk.domain.model.Currency
import pl.mwaszczuk.domain.model.PercentChange

data class Cryptocurrency(
    val id: String,
    val name: String,
    val symbol: String,
    val iconUrl: String,
    val price: Currency,
    val volume24h: Currency,
    val percentChange24h: PercentChange,
    val percentChange1h: PercentChange,
    val trend: Trend
)
