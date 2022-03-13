package pl.mwaszczuk.dashboard.model

import pl.mwaszczuk.dashboard.R
import pl.mwaszczuk.domain.model.CryptocurrencyData
import pl.mwaszczuk.domain.model.Currency
import pl.mwaszczuk.domain.model.PercentChange

class CryptocurrencyMapper {
    fun map(data: List<CryptocurrencyData>) =
        data.map {
            Cryptocurrency(
                it.id,
                it.name,
                it.symbol,
                it.iconUrl,
                Currency(R.string.currency_dollar_value, it.price),
                Currency(R.string.currency_dollar_value, it.volume24h),
                PercentChange(it.percentChange24h.toString(), it.percentChange24h >= 0),
                PercentChange(it.percentChange1h.toString(), it.percentChange1h >= 0),
            )
        }
}
