package pl.mwaszczuk.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import pl.mwaszczuk.dashboard.model.Cryptocurrency
import pl.mwaszczuk.dashboard.sortby.CryptoSortOption
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val useCase: DashboardUseCase
) : ViewModel() {

    val sortingOptions = useCase.currentSortingOption.asStateFlow()

    val state = useCase.cryptoDataFlow
        .shareIn(viewModelScope, SharingStarted.WhileSubscribed(), 1)

    fun onSortingOptionClicked(option: CryptoSortOption) {
        useCase.changeSortingOption(option)
    }

    fun refresh() {
        viewModelScope.launch {
            useCase.refresh()
        }
    }
}
