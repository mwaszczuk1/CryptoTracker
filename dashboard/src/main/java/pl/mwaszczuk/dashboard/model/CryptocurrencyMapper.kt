package pl.mwaszczuk.dashboard.model

import pl.mwaszczuk.dashboard.R
import pl.mwaszczuk.domain.model.CryptocurrencyData
import pl.mwaszczuk.domain.model.Currency
import pl.mwaszczuk.domain.model.PercentChange
import pl.mwaszczuk.formatters.BigNumberFormatter
import javax.inject.Inject

class CryptocurrencyMapper @Inject constructor(
    private val numberFormatter: BigNumberFormatter
) {
    fun map(
        data: List<CryptocurrencyData>,
        previousData: List<Cryptocurrency>?
    ) =
        data.map { currencyData ->
            val previousRecord = previousData?.firstOrNull { it.id == currencyData.id }
            val priceDiff = if (previousRecord == null) {
                null
            } else {
                currencyData.price - previousRecord.price.amount.toDouble()
            }
            Cryptocurrency(
                currencyData.id,
                currencyData.name,
                currencyData.symbol,
                currencyData.iconUrl,
                Currency(R.string.currency_dollar_value, currencyData.price.toBigDecimal()),
                numberFormatter.format(currencyData.volume24h),
                PercentChange(currencyData.percentChange24h.toString(), currencyData.percentChange24h >= 0),
                PercentChange(currencyData.percentChange1h.toString(), currencyData.percentChange1h >= 0),
                when {
                    priceDiff == null -> Trend.Flat
                    priceDiff > 0 -> Trend.Up
                    priceDiff < 0 -> Trend.Down
                    else -> Trend.Flat
                }
            )
        }
}
