package pl.mwaszczuk.domain.repository

import pl.mwaszczuk.domain.model.CryptocurrencyData
import pl.mwaszczuk.domain.safeApiCall
import pl.mwaszczuk.domain.unwrap
import pl.mwaszczuk.network.api.CryptoTickerApi
import javax.inject.Inject

class CryptoTickerRepository @Inject constructor(
    private val tickerApi: CryptoTickerApi
) {

    suspend fun getCryptoTickers() = safeApiCall {
        tickerApi.getCryptoTickers()
            .unwrap { response ->
                response.data.map {
                    CryptocurrencyData.from(it)
                }
            }
    }
}
