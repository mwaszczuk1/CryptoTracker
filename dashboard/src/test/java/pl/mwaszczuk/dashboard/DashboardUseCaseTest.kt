package pl.mwaszczuk.dashboard

import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.*
import pl.mwaszczuk.dashboard.model.CryptoSortOption
import pl.mwaszczuk.dashboard.model.CryptocurrencyMapper
import pl.mwaszczuk.domain.Dispatchers
import pl.mwaszczuk.domain.Result
import pl.mwaszczuk.domain.model.CryptocurrencyData
import pl.mwaszczuk.domain.repository.CryptoTickerRepository
import pl.mwaszczuk.test.TestCoroutineRule
import pl.mwaszczuk.test.test

class DashboardUseCaseTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val data = listOf(
        CryptocurrencyData("1", "Bitcoin", "BTC", "", 1.0,  1.0, 1.0, 1.0)
    )

    private val repository: CryptoTickerRepository = mock {
        onBlocking { it.getCryptoTickers() } doReturn Result.Success(data)
    }
    private val mapper: CryptocurrencyMapper = mock {
        whenever(it.map(any(), any())) doReturn emptyList()
    }
    private val dispatchers: Dispatchers = mock {
        whenever(it.io) doReturn testCoroutineRule.dispatcher
    }
    private val sortOption: CryptoSortOption = mock {
        whenever(it.sort(any())) doReturn emptyList()
    }

    private val useCase = DashboardUseCase(repository, mapper, dispatchers)

    @Test
    fun `test data ticker flow initial call and pinging API every 30 seconds`() = testCoroutineRule.runBlockingTest {
        val flow = useCase.cryptoDataFlow.test(this)
        advanceTimeBy(90000)
        verify(repository, times(4)).getCryptoTickers()
        Assert.assertEquals(4, flow.values.size)
        flow.finish()
    }

    @Test
    fun `test data refresh`() = testCoroutineRule.runBlockingTest {
        val flow = useCase.cryptoDataFlow.test(this)
        useCase.refresh()
        verify(repository, times(2)).getCryptoTickers()
        Assert.assertEquals(2, flow.values.size)
        flow.finish()
    }

    @Test
    fun `test proper sorting invoke`() = testCoroutineRule.runBlockingTest {
        useCase.changeSortingOption(sortOption)
        useCase.refresh()
        verify(sortOption).sort(data)
    }
}
