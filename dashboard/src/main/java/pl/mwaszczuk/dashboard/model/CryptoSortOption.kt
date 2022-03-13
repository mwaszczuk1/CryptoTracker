package pl.mwaszczuk.dashboard.model

import pl.mwaszczuk.domain.model.CryptocurrencyData

sealed class CryptoSortOption(
    val name: String
) {
    abstract fun sort(cryptoCurrencies: List<CryptocurrencyData>): List<CryptocurrencyData>

    class Name: CryptoSortOption("Name") {
        override fun sort(cryptoCurrencies: List<CryptocurrencyData>) =
            cryptoCurrencies.sortedBy { it.name }
    }

    class Volume24: CryptoSortOption("Volume 24h") {
        override fun sort(cryptoCurrencies: List<CryptocurrencyData>) =
            cryptoCurrencies.sortedBy { it.volume24h }
    }

    class PercentChange24: CryptoSortOption("Percent change 24h") {
        override fun sort(cryptoCurrencies: List<CryptocurrencyData>) =
            cryptoCurrencies.sortedBy { it.percentChange24h }
    }
}
