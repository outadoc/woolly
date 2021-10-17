package fr.outadoc.woolly.ui.common

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun WoollyTheme(
    isDarkMode: Boolean,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (isDarkMode) woollyDarkColors() else woollyLightColors(),
    ) {
        val systemUiController = rememberSystemUiController()

        val baseColor =
            if (isDarkMode) MaterialTheme.colors.surface
            else MaterialTheme.colors.primaryVariant

        val topColor = baseColor.elevated(AppBarDefaults.TopAppBarElevation)
        val bottomColor = baseColor.elevated(AppBarDefaults.BottomAppBarElevation)

        LaunchedEffect(topColor) {
            systemUiController.setStatusBarColor(topColor)
        }

        LaunchedEffect(bottomColor) {
            systemUiController.setNavigationBarColor(bottomColor)
        }

        content()
    }
}

fun woollyLightColors() = lightColors(
    primary = Color(0xff2196f3),
    primaryVariant = Color(0xff0069c0),
    onPrimary = Color(0xffffffff),
    secondary = Color(0xff9ccc65),
    secondaryVariant = Color(0xff6b9b37),
    onSecondary = Color(0xff000000)
)

fun woollyDarkColors() = darkColors(
    primary = Color(0xff2196f3),
    primaryVariant = Color(0xff0069c0),
    onPrimary = Color(0xffffffff),
    secondary = Color(0xff9ccc65),
    secondaryVariant = Color(0xff6b9b37),
    onSecondary = Color(0xff000000)
)

@Composable
private fun Color.elevated(elevation: Dp): Color {
    val elevationOverlay = LocalElevationOverlay.current
    return if (this == MaterialTheme.colors.surface && elevationOverlay != null) {
        elevationOverlay.apply(this, elevation)
    } else {
        this
    }
}

object WoollyTheme {
    val BoostColor = Color(0xff2b90d9)
    val FavouriteColor = Color(0xffca8f04)
    val AvatarShape = CircleShape
}
