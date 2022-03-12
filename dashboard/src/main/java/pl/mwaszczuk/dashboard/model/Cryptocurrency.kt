package pl.mwaszczuk.dashboard.model

data class Cryptocurrency(
    val id: String,
    val name: String,
    val symbol: String,
    val iconUrl: String,
    val price: String,
    val percentChange24h: String,
    val percentChange1h: String
)
