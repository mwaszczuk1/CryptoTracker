package pl.mwaszczuk.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import pl.mwaszczuk.dashboard.components.CryptocurrencyItem
import pl.mwaszczuk.dashboard.components.CryptocurrencyItemLoading
import pl.mwaszczuk.dashboard.model.Cryptocurrency
import pl.mwaszczuk.design.components.Button
import pl.mwaszczuk.design.extensions.DesignDrawables
import pl.mwaszczuk.design.theme.GrayLight
import pl.mwaszczuk.design.theme.Red
import pl.mwaszczuk.domain.ViewState
import pl.mwaszczuk.navigation.Destination

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel,
    navController: NavController
) {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = MaterialTheme.colors.isLight

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = GrayLight,
            darkIcons = useDarkIcons
        )
    }

    val state by viewModel.state.collectAsState(ViewState.Loading)

    if (state is ViewState.Error) {
        ErrorLayout()
    } else {
        DashboardLayout(state, navController)
    }
}

@Composable
fun DashboardLayout(
    state: ViewState<List<Cryptocurrency>>,
    navController: NavController
) {
    val lazyListState = rememberLazyListState()
    Box {
        LazyColumn(
            state = lazyListState,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
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
                    items = state.data,
                    key = { it.id }
                ) {
                    CryptocurrencyItem(
                        modifier = Modifier
                            .animateItemPlacement(),
                        item = it
                    )
                }
            } else {
                items(8) {
                    CryptocurrencyItemLoading()
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

@Composable
fun ErrorLayout() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Icon(
                modifier = Modifier
                    .requiredSize(48.dp)
                    .align(Alignment.CenterHorizontally),
                painter = painterResource(DesignDrawables.ic_baseline_error_24),
                contentDescription = "errorIcon",
                tint = Red
            )
            Text(
                modifier = Modifier
                    .padding(top = 16.dp),
                text = stringResource(R.string.error_message),
                style = MaterialTheme.typography.h2
            )
        }
    }
}
