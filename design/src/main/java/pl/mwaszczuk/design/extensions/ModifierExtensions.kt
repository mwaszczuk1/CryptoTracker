package pl.mwaszczuk.design.extensions

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import com.google.accompanist.placeholder.PlaceholderDefaults
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.material.placeholder
import pl.mwaszczuk.design.theme.GrayDark
import pl.mwaszczuk.design.theme.GrayLight

@Composable
fun Modifier.loadingPlaceholder(
    isVisible: Boolean,
    fillMaxWidth: Boolean = false,
    shape: Shape? = null
) = let {
    if (fillMaxWidth) {
        this.fillMaxWidth()
    } else {
        this
    }
}.placeholder(
    visible = isVisible,
    color = GrayLight,
    highlight = PlaceholderHighlight.fade(
        highlightColor = GrayDark,
        animationSpec = PlaceholderDefaults.fadeAnimationSpec,
    ),
    shape = shape ?: RectangleShape
)
