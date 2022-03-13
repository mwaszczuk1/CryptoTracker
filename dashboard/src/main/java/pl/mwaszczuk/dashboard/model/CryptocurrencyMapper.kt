package pl.mwaszczuk.dashboard.model

import pl.mwaszczuk.domain.CryptocurrencyData

class CryptocurrencyMapper {
    fun map(data: List<CryptocurrencyData>) =
        data.map {
            Cryptocurrency(
                it.id,
                it.name,
                it.symbol,
                "https://cryptoicons.org/api/icon/eth/200",
                it.price.toString(),
                it.volume24h.toString(),
                it.percentChange24h.toString(),
                it.percentChange1h.toString()
            )
        }
}
