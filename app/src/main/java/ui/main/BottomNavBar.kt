package navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.weatherapp.R

@Composable
fun BottomNavBar(navController: NavController) {

    val items = listOf(BottomNavItem.Home, BottomNavItem.Weekly, BottomNavItem.Settings)

    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination?.route

        items.forEach {
            BottomNavigationItem(
                selected = currentDestination == it.route,
                icon = {
                    Icon(
                        it.icon,
                        contentDescription = stringResource(id = it.contentDescriptionId),
                        modifier = Modifier.size(dimensionResource(id = R.dimen.large))
                    )
                },
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