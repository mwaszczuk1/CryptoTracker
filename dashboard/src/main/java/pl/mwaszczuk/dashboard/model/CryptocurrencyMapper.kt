package pl.mwaszczuk.dashboard.model

import pl.mwaszczuk.dashboard.R
import pl.mwaszczuk.domain.model.CryptocurrencyData
import pl.mwaszczuk.domain.model.Currency
import pl.mwaszczuk.domain.model.PercentChange

class CryptocurrencyMapper {
    fun map(
        data: List<CryptocurrencyData>,
        previousData: List<Cryptocurrency>?
    ) =
        data.map { data ->
            val previousRecord = previousData?.firstOrNull { it.id == data.id }
            val priceDiff = if (previousRecord == null) {
                null
            } else {
                previousRecord.price.amount - data.price
            }
            Cryptocurrency(
                data.id,
                data.name,
                data.symbol,
                data.iconUrl,
                Currency(R.string.currency_dollar_value, data.price),
                Currency(R.string.currency_dollar_value, data.volume24h),
                PercentChange(data.percentChange24h.toString(), data.percentChange24h >= 0),
                PercentChange(data.percentChange1h.toString(), data.percentChange1h >= 0),
                when {
                    priceDiff == null -> Trend.Flat
                    priceDiff > 0 -> Trend.Up
                    priceDiff < 0 -> Trend.Down
                    else -> Trend.Flat
                }
            )
        }
}
