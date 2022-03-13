package pl.mwaszczuk.dashboard.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import pl.mwaszczuk.design.extensions.DesignDrawables
import pl.mwaszczuk.design.theme.GrayDark
import pl.mwaszczuk.design.theme.Green
import pl.mwaszczuk.design.theme.Red

enum class Trend(
    @DrawableRes val iconRes: Int,
    val iconColor: Color
) {
    Up(
        iconRes = DesignDrawables.ic_baseline_trending_up_24,
        iconColor = Green
    ),
    Flat(
        iconRes = DesignDrawables.ic_baseline_trending_flat_24,
        iconColor = GrayDark
    ),
    Down(
        iconRes = DesignDrawables.ic_baseline_trending_down_24,
        iconColor = Red
    )
}
