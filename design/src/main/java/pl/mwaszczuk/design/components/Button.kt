package pl.mwaszczuk.design.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import pl.mwaszczuk.design.defaults.ButtonDefaults
import androidx.compose.material.ButtonDefaults as ComposeButtonDefaults

@Composable
fun Button(
    modifier: Modifier = Modifier,
    @DrawableRes trailingIcon: Int? = null,
    text: String = "",
    onClick: () -> Unit,
    isTextVisible: Boolean = true
) {
    androidx.compose.material.Button(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(ButtonDefaults.CORNER),
        elevation = ComposeButtonDefaults.elevation()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (trailingIcon != null) {
                Icon(
                    painter = painterResource(trailingIcon),
                    contentDescription = ButtonDefaults.TRAILING_ICON_DESCRIPTION
                )
                AnimatedVisibility(
                    visible = isTextVisible,
                    enter = expandHorizontally(animationSpec = tween(300)),
                    exit = shrinkHorizontally(animationSpec = tween(300))
                ) {
                    Text(
                        modifier = Modifier
                            .padding(start = 8.dp),
                        text = text,
                        style = MaterialTheme.typography.button
                    )
                }
            }
        }
    }
}
