package pl.mwaszczuk.dashboard

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
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

class DashboardUseCaseTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

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
        whenever(it.io) doReturn mainCoroutineRule.dispatcher
    }
    private val sortOption: CryptoSortOption = mock {
        whenever(it.sort(any())) doReturn emptyList()
    }

    private val useCase = DashboardUseCase(repository, mapper, dispatchers)

    @Test
    fun `test data ticker flow initial call and pinging API every 30 seconds`() = mainCoroutineRule.runBlockingTest {
        val job = launch { useCase.cryptoDataFlow.collect {  } }
        advanceTimeBy(90000)
        job.cancel()
        verify(repository, times(4)).getCryptoTickers()
    }

    @Test
    fun `test data refresh`() = mainCoroutineRule.runBlockingTest {
        val job = launch { useCase.cryptoDataFlow.collect {  } }
        job.cancel()
        useCase.refresh()
        verify(repository, times(2)).getCryptoTickers()
    }

    @Test
    fun `test proper sorting invoke`() = mainCoroutineRule.runBlockingTest {
        useCase.changeSortingOption(sortOption)
        useCase.refresh()
        verify(sortOption).sort(data)
    }
}
