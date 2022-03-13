package pl.mwaszczuk.network.api

import pl.mwaszczuk.network.response.CryptoTickerResponse
import pl.mwaszczuk.network.response.DataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoTickerApi {

    @GET(TICKER_PREFIX)
    suspend fun getCryptoTickers(
        @Query("limit") limit: Int = 20
    ): Response<DataResponse<List<CryptoTickerResponse>>>

    companion object {
        private const val TICKER_PREFIX = "tickers/"
    }
}