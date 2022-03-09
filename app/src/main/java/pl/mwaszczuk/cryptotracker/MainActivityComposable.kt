package pl.mwaszczuk.cryptotracker

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import pl.mwaszczuk.splash.SplashScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import pl.mwaszczuk.cryptotracker.defaults.NavigationDefaults
import pl.mwaszczuk.dashboard.DashboardScreen
import pl.mwaszczuk.navigation.Destination

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainActivityComposable() {
    val navController = rememberAnimatedNavController()

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
                DashboardScreen()
            }
        }
    }
}
