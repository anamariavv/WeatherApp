package navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigator(navController: NavController) {

    val items = listOf(BottomNavItem.Home, BottomNavItem.Weekly, BottomNavItem.Settings)

    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination?.route

        items.forEach {
            BottomNavigationItem(selected = currentDestination == it.route,
                icon = { Icon(it.icon, contentDescription = null) },
                onClick = {
                    navController.navigate(it.route) {
                        navController.graph.startDestinationRoute?.let { startRoute ->
                            popUpTo(startRoute) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}