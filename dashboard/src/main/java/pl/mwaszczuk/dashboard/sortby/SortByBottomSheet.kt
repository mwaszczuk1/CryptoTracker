package pl.mwaszczuk.dashboard.sortby

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.mwaszczuk.dashboard.DashboardViewModel
import pl.mwaszczuk.dashboard.R
import pl.mwaszczuk.dashboard.model.CryptoSortOption
import pl.mwaszczuk.design.extensions.DesignDrawables
import pl.mwaszczuk.design.theme.GrayDark

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
                .requiredWidth(48.dp)
                .padding(bottom = 24.dp)
                .clip(RoundedCornerShape(24.dp))
                .align(Alignment.CenterHorizontally),
            color = GrayDark,
            thickness = 4.dp
        )
        Text(
            modifier = Modifier
                .padding(bottom = 16.dp),
            text = stringResource(R.string.sort_by),
            style = MaterialTheme.typography.h2
        )
        sortingOptions.let { (options, selectedOption) ->
            options.forEachIndexed { index, option ->
                SortingOption(
                    option = option,
                    isSelected = option == selectedOption,
                    onClick = viewModel::onSortingOptionClicked
                )
                if (index < options.size - 1) {
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth(),
                        color = GrayDark,
                        thickness = 0.5.dp
                    )
                }
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
            .clickable { onClick(option) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .padding(top = 16.dp, bottom = 16.dp)
                .weight(1f),
            text = option.name,
            style = if (isSelected) {
                MaterialTheme.typography.h3.copy(color = MaterialTheme.colors.primary)
            } else {
                MaterialTheme.typography.body1.copy(fontSize = 14.sp)
            }
        )
        AnimatedVisibility(
            visible = isSelected,
            enter = fadeIn(animationSpec = tween(300)),
            exit = fadeOut(animationSpec = tween(300))
        ) {
            Icon(
                painter = painterResource(DesignDrawables.ic_baseline_done_24),
                contentDescription = "selectedTick",
                tint = MaterialTheme.colors.primary
            )
        }
    }
}