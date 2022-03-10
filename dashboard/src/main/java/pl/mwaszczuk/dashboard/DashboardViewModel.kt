package pl.mwaszczuk.dashboard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import pl.mwaszczuk.dashboard.model.Cryptocurrency
import javax.inject.Inject

class DashboardViewModel @Inject constructor() : ViewModel() {


    private val mockCryptos = listOf(
        Cryptocurrency("1", "Bitcoin", "BTC"),
        Cryptocurrency("2", "Ethereum", "ETH"),
        Cryptocurrency("3", "Bitcoin", "BTC"),
        Cryptocurrency("4", "Bitcoin", "BTC"),
        Cryptocurrency("5", "Bitcoin", "BTC"),
        Cryptocurrency("6", "Bitcoin", "BTC"),
        Cryptocurrency("7", "Bitcoin", "BTC"),
        Cryptocurrency("8", "Bitcoin", "BTC"),
        Cryptocurrency("9", "Bitcoin", "BTC"),
        Cryptocurrency("10", "Bitcoin", "BTC"),
        Cryptocurrency("11", "Bitcoin", "BTC"),
    )

    private val _state = MutableStateFlow(mockCryptos)
    val state = _state.asStateFlow()
}
