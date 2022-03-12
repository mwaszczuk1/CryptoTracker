package pl.mwaszczuk.dashboard

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import pl.mwaszczuk.dashboard.components.CryptocurrencyItem
import pl.mwaszczuk.dashboard.model.Cryptocurrency
import pl.mwaszczuk.design.components.Button
import pl.mwaszczuk.design.extensions.DesignDrawables
import pl.mwaszczuk.domain.ViewState
import pl.mwaszczuk.navigation.Destination

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel,
    navController: NavController
) {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = MaterialTheme.colors.isLight
    val backgroundColor = MaterialTheme.colors.background

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = backgroundColor,
            darkIcons = useDarkIcons
        )
    }

    val lazyListState = rememberLazyListState()

    val state by viewModel.state.collectAsState(ViewState.Loading)

    Box {
        LazyColumn(
            state = lazyListState,
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    modifier = Modifier
                        .padding(bottom = 16.dp),
                    text = "CryptoTracker",
                    style = MaterialTheme.typography.h1.copy(fontSize = 28.sp)
                )
            }
            if (state is ViewState.Success) {
                items(
                    items = (state as ViewState.Success<List<Cryptocurrency>>).data,
                    key = { it.id }
                ) {
                    CryptocurrencyItem(
                        modifier = Modifier
                            .animateItemPlacement(),
                        item = it
                    )
                }
            }

        }
        Button(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp),
            trailingIcon = DesignDrawables.ic_baseline_sort_24,
            text = stringResource(R.string.sort_by),
            onClick = { navController.navigate(Destination.SortByBottomSheet.route) },
            isTextVisible = lazyListState.firstVisibleItemIndex == 0
        )
    }

}
