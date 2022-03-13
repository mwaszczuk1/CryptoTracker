package pl.mwaszczuk.dashboard

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.*
import pl.mwaszczuk.dashboard.model.CryptoSortOption
import pl.mwaszczuk.test.TestCoroutineRule

class DashboardViewModelTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val useCase: DashboardUseCase = mock()
    private val viewModel = DashboardViewModel(useCase)

    @Test
    fun `test refresh method invoke`() = testCoroutineRule.runBlockingTest {
        viewModel.refresh()
        verify(useCase).refresh()
    }

    @Test
    fun `test change sorting method invoke`() = testCoroutineRule.runBlockingTest {
        val option = CryptoSortOption.Volume24()
        viewModel.onSortingOptionClicked(option)
        verify(useCase).changeSortingOption(option)
    }
}
