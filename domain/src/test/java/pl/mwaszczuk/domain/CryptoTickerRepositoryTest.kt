package pl.mwaszczuk.domain

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.*
import pl.mwaszczuk.domain.repository.CryptoTickerRepository
import pl.mwaszczuk.network.api.CryptoTickerApi
import pl.mwaszczuk.network.response.CryptoTickerResponse
import pl.mwaszczuk.network.response.DataResponse
import retrofit2.HttpException
import retrofit2.Response

class CryptoTickerRepositoryTest {

    private val tickerApi: CryptoTickerApi = mock()
    private val repository = CryptoTickerRepository(tickerApi)

    @Test
    fun `test success api response`() = runBlockingTest {
        whenever(tickerApi.getCryptoTickers(any())) doReturn Response.success(
            DataResponse(emptyList())
        )

        val result = repository.getCryptoTickers()

        verify(tickerApi).getCryptoTickers()
        Assert.assertEquals(emptyList<CryptoTickerResponse>(), (result as? Result.Success)?.data)
    }

    @Test
    fun `test error api response`() = runBlockingTest {
        whenever(tickerApi.getCryptoTickers(any())) doReturn Response.error(404, "".toResponseBody())

        val result = repository.getCryptoTickers()

        verify(tickerApi).getCryptoTickers()
        Assert.assertEquals(404, ((result as? Result.Error)?.error as? HttpException)?.code())
    }

    @Test
    fun `test empty api response`() = runBlockingTest {
        val response: DataResponse<List<CryptoTickerResponse>>? = null
        whenever(tickerApi.getCryptoTickers(any())) doReturn Response.success(204, response)

        val result = repository.getCryptoTickers()

        verify(tickerApi).getCryptoTickers()
        Assert.assertEquals(Result.Empty, result)
    }
}
