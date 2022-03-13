package pl.mwaszczuk.domain

import pl.mwaszczuk.network.response.CryptoTickerResponse

data class CryptocurrencyData(
    val id: String,
    val name: String,
    val symbol: String,
    val price: Double,
    val volume24h: Double,
    val percentChange24h: Double,
    val percentChange1h: Double
) {
    companion object {
        fun from(response: CryptoTickerResponse) =
            CryptocurrencyData(
                id = response.id,
                name = response.name,
                symbol = response.symbol,
                price = response.priceUsd,
                volume24h = response.volume24,
                percentChange24h = response.percentChange24h,
                percentChange1h = response.percentChange1h
            )
    }
}
