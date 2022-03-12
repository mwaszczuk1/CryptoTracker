package pl.mwaszczuk.dashboard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import pl.mwaszczuk.dashboard.model.Cryptocurrency
import javax.inject.Inject

class DashboardViewModel @Inject constructor() : ViewModel() {


    private val mockCryptos = listOf(
        Cryptocurrency("1", "Bitcoin", "BTC", "https://cryptoicons.org/api/icon/eth/200", "38715.15$", "-1.21%", "-0.17%"),
        Cryptocurrency("2", "Bitcoin", "BTC", "https://cryptoicons.org/api/icon/eth/200", "38715.15$", "-1.21%", "-0.17%"),
        Cryptocurrency("3", "Bitcoin", "BTC", "https://cryptoicons.org/api/icon/eth/200", "38715.15$", "-1.21%", "-0.17%"),
        Cryptocurrency("4", "Bitcoin", "BTC", "https://cryptoicons.org/api/icon/eth/200", "38715.15$", "-1.21%", "-0.17%"),
        Cryptocurrency("5", "Bitcoin", "BTC", "https://cryptoicons.org/api/icon/eth/200", "38715.15$", "-1.21%", "-0.17%"),
        Cryptocurrency("6", "Bitcoin", "BTC", "https://cryptoicons.org/api/icon/eth/200", "38715.15$", "-1.21%", "-0.17%"),
        Cryptocurrency("7", "Bitcoin", "BTC", "https://cryptoicons.org/api/icon/eth/200", "38715.15$", "-1.21%", "-0.17%"),
        Cryptocurrency("8", "Bitcoin", "BTC", "https://cryptoicons.org/api/icon/eth/200", "38715.15$", "-1.21%", "-0.17%"),
        Cryptocurrency("9", "Bitcoin", "BTC", "https://cryptoicons.org/api/icon/eth/200", "38715.15$", "-1.21%", "-0.17%"),
        Cryptocurrency("10", "Bitcoin", "BTC", "https://cryptoicons.org/api/icon/eth/200", "38715.15$", "-1.21%", "-0.17%"),
    )

    private val _state = MutableStateFlow(mockCryptos)
    val state = _state.asStateFlow()
}
