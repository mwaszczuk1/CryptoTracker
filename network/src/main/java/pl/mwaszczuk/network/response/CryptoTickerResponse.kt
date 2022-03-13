package pl.mwaszczuk.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CryptoTickerResponse(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("symbol")
    val symbol: String,
    @SerialName("percent_change_1h")
    val percentChange1h: Double,
    @SerialName("percent_change_24h")
    val percentChange24h: Double,
    @SerialName("price_usd")
    val priceUsd: Double,
    @SerialName("volume24")
    val volume24: Double,
)
