package pl.mwaszczuk.domain.repository

import pl.mwaszczuk.domain.CryptocurrencyData
import pl.mwaszczuk.domain.unwrap
import pl.mwaszczuk.network.api.CryptoTickerApi
import javax.inject.Inject

class CryptoTickerRepository @Inject constructor(
    private val tickerApi: CryptoTickerApi
) {

    suspend fun getCryptoTickers() = tickerApi.getCryptoTickers()
        .unwrap { response ->
            response.map {
                CryptocurrencyData.from(it)
            }
        }
}
