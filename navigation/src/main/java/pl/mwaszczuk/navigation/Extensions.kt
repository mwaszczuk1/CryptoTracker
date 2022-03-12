package pl.mwaszczuk.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController

@Composable
inline fun <reified VM : ViewModel> SharedViewModel(
    entry: NavBackStackEntry,
    navController: NavController
): VM {
    val rootEntry = remember { navController.getBackStackEntry(entry.destination.parent?.id!!) }
    return hiltViewModel<VM>(rootEntry)
}
