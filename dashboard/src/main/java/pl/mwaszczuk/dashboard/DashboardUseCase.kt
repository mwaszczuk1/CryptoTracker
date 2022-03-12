package pl.mwaszczuk.dashboard

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import pl.mwaszczuk.dashboard.model.CryptocurrencyMapper
import pl.mwaszczuk.dashboard.sortby.CryptoSortOption
import pl.mwaszczuk.domain.CryptocurrencyData
import pl.mwaszczuk.domain.ViewState
import javax.inject.Inject

class DashboardUseCase @Inject constructor(
    private val mapper: CryptocurrencyMapper
) {

    val currentSortingOption = CryptoSortOption.Name()

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
            "Bitcoin",
            "BTC",
            "https://cryptoicons.org/api/icon/eth/200",
            38715.15,
            5555.0,
            -1.21,
            -0.17
        ),
        CryptocurrencyData(
            "3",
            "Bitcoin",
            "BTC",
            "https://cryptoicons.org/api/icon/eth/200",
            38715.15,
            5555.0,
            -1.21,
            -0.17
        ),
        CryptocurrencyData(
            "4",
            "Bitcoin",
            "BTC",
            "https://cryptoicons.org/api/icon/eth/200",
            38715.15,
            5555.0,
            -1.21,
            -0.17
        ),
        CryptocurrencyData(
            "5",
            "Bitcoin",
            "BTC",
            "https://cryptoicons.org/api/icon/eth/200",
            38715.15,
            5555.0,
            -1.21,
            -0.17
        ),
        CryptocurrencyData(
            "6",
            "Bitcoin",
            "BTC",
            "https://cryptoicons.org/api/icon/eth/200",
            38715.15,
            5555.0,
            -1.21,
            -0.17
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

    val cryptoDataFlow = flow {
        while (true) {
            val data = fetchAsViewState {
                mapper.map(
                    currentSortingOption.sort(mockCryptos)
                )
            }
            emit(data)
            delay(CRYPTO_PINGING_DELAY_MILLIS)
        }
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

    companion object {
        private const val CRYPTO_PINGING_DELAY_MILLIS = 30000L
    }
}
