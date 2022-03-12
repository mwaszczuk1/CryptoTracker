package pl.mwaszczuk.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import pl.mwaszczuk.dashboard.model.Cryptocurrency
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val useCase: DashboardUseCase
) : ViewModel() {

    val state = useCase.cryptoDataFlow
        .shareIn(viewModelScope, SharingStarted.WhileSubscribed(), 1)
}
