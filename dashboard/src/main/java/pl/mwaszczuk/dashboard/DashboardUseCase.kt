package pl.mwaszczuk.dashboard

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import pl.mwaszczuk.dashboard.model.Cryptocurrency
import pl.mwaszczuk.dashboard.model.CryptocurrencyMapper
import pl.mwaszczuk.dashboard.model.CryptoSortOption
import pl.mwaszczuk.domain.CryptocurrencyData
import pl.mwaszczuk.domain.ViewState
import javax.inject.Inject

class DashboardUseCase @Inject constructor(
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

    private val mockCryptos = listOf(
        CryptocurrencyData(
            "1",
            "Bitcoin",
            "BTC",
            "https://cryptoicons.org/api/icon/eth/200",
            38715.15,
            5555.0,
            -1.21,
            -0.17
        ),
        CryptocurrencyData(
            "2",
            "Ethereum",
            "ETH",
            "https://cryptoicons.org/api/icon/eth/200",
            3871.15,
            555.0,
            -1.2,
            0.5
        ),
        CryptocurrencyData(
            "3",
            "Luna",
            "LUNA",
            "https://cryptoicons.org/api/icon/eth/200",
            99.15,
            55.0,
            1.21,
            0.17
        ),
        CryptocurrencyData(
            "4",
            "Hedera Hashgraph",
            "HBAR",
            "https://cryptoicons.org/api/icon/eth/200",
            0.2015,
            5.0,
            2.15,
            0.55
        ),
        CryptocurrencyData(
            "5",
            "Binance Coin",
            "BNB",
            "https://cryptoicons.org/api/icon/eth/200",
            377.0,
            55525.0,
            -1.213,
            -0.175
        ),
        CryptocurrencyData(
            "6",
            "Solana",
            "SOL",
            "https://cryptoicons.org/api/icon/eth/200",
            100.15,
            142.0,
            -1.211,
            0.1753
        ),
        CryptocurrencyData(
            "7",
            "Bitcoin",
            "BTC",
            "https://cryptoicons.org/api/icon/eth/200",
            38715.15,
            5555.0,
            -1.21,
            -0.17
        ),
        CryptocurrencyData(
            "8",
            "Bitcoin",
            "BTC",
            "https://cryptoicons.org/api/icon/eth/200",
            38715.15,
            5555.0,
            -1.21,
            -0.17
        ),
        CryptocurrencyData(
            "9",
            "Bitcoin",
            "BTC",
            "https://cryptoicons.org/api/icon/eth/200",
            38715.15,
            5555.0,
            -1.21,
            -0.17
        ),
        CryptocurrencyData(
            "10",
            "Bitcoin",
            "BTC",
            "https://cryptoicons.org/api/icon/eth/200",
            38715.15,
            5555.0,
            -1.21,
            -0.17
        )
    )

    private val cryptoDataRefresh = MutableSharedFlow<ViewState<List<Cryptocurrency>>>(replay = 1)
    private val cryptoDataTicker = flow {
        while (true) {
            val data = fetchAsViewState {
                mapper.map(
                    currentSortingOption.value.second.sort(mockCryptos)
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

    inline fun <T : Any> fetchAsViewState(
        block: () -> T
    ): ViewState<T> = try {
        ViewState.Success(block())
    } catch (e: Throwable) {
        ViewState.Error(e)
    }

    suspend fun refresh() {
        cryptoDataRefresh.emit(
            fetchAsViewState {
                mapper.map(
                    currentSortingOption.value.second.sort(mockCryptos)
                )
            }
        )
    }

    companion object {
        private const val CRYPTO_PINGING_DELAY_MILLIS = 30000L
    }
}
