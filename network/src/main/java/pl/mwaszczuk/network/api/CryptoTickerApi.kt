package pl.mwaszczuk.network.api

import pl.mwaszczuk.network.response.CryptoTickerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoTickerApi {

    @GET(TICKER_PREFIX)
    fun getCryptoTickers(
        @Query("limit") limit: Int = 20
    ): Response<List<CryptoTickerResponse>>

    companion object {
        private const val TICKER_PREFIX = "tickers/"
    }
}