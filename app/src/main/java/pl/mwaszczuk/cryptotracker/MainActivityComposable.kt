package pl.mwaszczuk.cryptotracker

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import pl.mwaszczuk.splash.SplashScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.bottomSheet
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import pl.mwaszczuk.cryptotracker.defaults.NavigationDefaults
import pl.mwaszczuk.dashboard.DashboardScreen
import pl.mwaszczuk.dashboard.sortby.SortByBottomSheet
import pl.mwaszczuk.navigation.Destination

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialNavigationApi::class)
@Composable
fun MainActivityComposable() {
    val navController = rememberAnimatedNavController()
    val bottomSheetNavigator = rememberBottomSheetNavigator()
    navController.navigatorProvider.addNavigator(bottomSheetNavigator)

    ModalBottomSheetLayout(
        modifier = Modifier
            .fillMaxSize(),
        bottomSheetNavigator = bottomSheetNavigator,
        sheetShape = RoundedCornerShape(
            topStart = 20.dp,
            topEnd = 20.dp,
        )
    ) {
        AnimatedNavHost(
            modifier = Modifier
                .fillMaxSize(),
            navController = navController,
            startDestination = Destination.Splash.route,
            route = Destination.MainNavGraph.route,
            enterTransition = {
                fadeIn(
                    animationSpec = tween(
                        NavigationDefaults.ANIM_DURATION,
                        NavigationDefaults.ANIM_DURATION
                    )
                )
            },
            exitTransition = {
                fadeOut(
                    animationSpec = tween(NavigationDefaults.ANIM_DURATION)
                )
            }
        ) {
            composable(
                route = Destination.Splash.route
            ) {
                SplashScreen(navController)
            }
            navigation(
                startDestination = Destination.Dashboard.route,
                route = Destination.DashboardNavGraph.route
            ) {
                composable(
                    route = Destination.Dashboard.route
                ) {
                    DashboardScreen(navController = navController)
                }
                bottomSheet(
                    route = Destination.SortByBottomSheet.route
                ) {
                    SortByBottomSheet()
                }
            }
        }
    }
}
