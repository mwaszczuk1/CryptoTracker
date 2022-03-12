package pl.mwaszczuk.domain

data class CryptocurrencyData(
    val id: String,
    val name: String,
    val symbol: String,
    val iconUrl: String,
    val price: Double,
    val volume24h: Double,
    val percentChange24h: Double,
    val percentChange1h: Double
)
