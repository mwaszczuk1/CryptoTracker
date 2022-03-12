package pl.mwaszczuk.dashboard.sortby

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import pl.mwaszczuk.dashboard.DashboardViewModel
import pl.mwaszczuk.design.extensions.DesignDrawables

@Composable
fun SortByBottomSheet(
    viewModel: DashboardViewModel
) {
    
    DisposableEffect(key1 = viewModel) {
        onDispose { viewModel.refresh() }
    }
    val sortingOptions by viewModel.sortingOptions.collectAsState()

    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Divider(
            modifier = Modifier
                .requiredWidth(32.dp)
                .padding(bottom = 24.dp),
            color = MaterialTheme.colors.background,
            thickness = 4.dp
        )
        sortingOptions.let { (options, selectedOption) ->
            options.forEach { option ->
                SortingOption(
                    option = option,
                    isSelected = option == selectedOption,
                    onClick = viewModel::onSortingOptionClicked
                )
            }
        }
    }
}

@Composable
fun SortingOption(
    option: CryptoSortOption,
    isSelected: Boolean,
    onClick: (option: CryptoSortOption) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(option) }
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = option.name
        )
        AnimatedVisibility(
            visible = isSelected,
            enter = fadeIn(animationSpec = tween(300)),
            exit = fadeOut(animationSpec = tween(300))
        ) {
            Icon(
                painter = painterResource(DesignDrawables.ic_baseline_done_24),
                contentDescription = "selectedTick"
            )
        }
    }
}