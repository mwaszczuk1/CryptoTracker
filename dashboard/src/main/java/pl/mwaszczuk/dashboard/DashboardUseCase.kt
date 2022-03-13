package pl.mwaszczuk.dashboard

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import pl.mwaszczuk.dashboard.model.Cryptocurrency
import pl.mwaszczuk.dashboard.model.CryptocurrencyMapper
import pl.mwaszczuk.dashboard.model.CryptoSortOption
import pl.mwaszczuk.domain.ViewState
import pl.mwaszczuk.domain.mapAsViewState
import pl.mwaszczuk.domain.repository.CryptoTickerRepository
import javax.inject.Inject

class DashboardUseCase @Inject constructor(
    private val repository: CryptoTickerRepository,
    private val mapper: CryptocurrencyMapper
) {

    private val sortingOptions = listOf(
        CryptoSortOption.Name(),
        CryptoSortOption.Volume24(),
        CryptoSortOption.PercentChange24()
    )

    val currentSortingOption = MutableStateFlow(sortingOptions to sortingOptions[0])

    fun changeSortingOption(option: CryptoSortOption) {
        currentSortingOption.value = sortingOptions to option
    }

    private val cryptoDataRefresh = MutableSharedFlow<ViewState<List<Cryptocurrency>>>(replay = 1)
    private val cryptoDataTicker = flow {
        while (true) {
            val data = repository.getCryptoTickers().mapAsViewState {
                mapper.map(
                    currentSortingOption.value.second.sort(
                        it
                    )
                )
            }
            emit(data)
            delay(CRYPTO_PINGING_DELAY_MILLIS)
        }
    }
    @OptIn(ExperimentalCoroutinesApi::class)
    val cryptoDataFlow = channelFlow {
        launch {
            cryptoDataTicker.collect { send(it) }
        }
        cryptoDataRefresh.collect { send(it) }
    }
        .conflate()
        .flowOn(Dispatchers.IO)

    suspend fun refresh() {
        cryptoDataRefresh.emit(
            repository.getCryptoTickers().mapAsViewState {
                mapper.map(
                    currentSortingOption.value.second.sort(
                        it
                    )
                )
            }
        )
    }

    companion object {
        private const val CRYPTO_PINGING_DELAY_MILLIS = 30000L
    }
}
