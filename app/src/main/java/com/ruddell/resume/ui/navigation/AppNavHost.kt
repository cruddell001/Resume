package com.ruddell.resume.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ruddell.resume.ui.Details
import com.ruddell.resume.ui.HomeView
import com.ruddell.resume.ui.details.DetailsView
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val NAV_ARG_1 = "arg1"

enum class Destination(val route: String) {
    Home("home"),
    Detail("detail/{$NAV_ARG_1}")
}

fun NavBackStackEntry.arg1(): String? = arguments?.getString("arg1", null)

fun NavHostController.navigate(destination: Destination, arg1: String = "") {
    navigate(destination.route.replace("{$NAV_ARG_1}", arg1))
}

@Composable
fun AppNavHost(controller: NavHostController) {
    val showMainScreenContent = remember { mutableStateOf(false) }
    LaunchedEffect(key1 = Unit) {
        launch {
            delay(1500L)
            showMainScreenContent.value = true
        }
    }
    NavHost(navController = controller, startDestination = "home" ) {
        destinationComposable(Destination.Home) {
            HomeView(showMainScreenContent.value) {
                showMainScreenContent.value = !showMainScreenContent.value
            }
        }
        destinationComposable(Destination.Detail) {
            val details = Details.values()[it.arg1()?.toIntOrNull() ?: 0]
            DetailsView(details)
        }
    }
}

/**
 * Helper method allowing Destination enum to be passed in rather than hard-coded strings
 */
fun NavGraphBuilder.destinationComposable(
    destination: Destination,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(destination.route, arguments, deepLinks, content)
}

