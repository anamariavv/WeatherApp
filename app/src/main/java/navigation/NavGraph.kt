package navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ui.cities.CitiesScreen
import ui.home.HomeScreen
import ui.settings.SettingsScreen
import ui.weekly.WeeklyScreen

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = "route_home") {
        composable(NavigationDirection.Home.destination) {
            HomeScreen()
        }

        composable(NavigationDirection.Settings.destination) {
            SettingsScreen()
        }

        composable(NavigationDirection.Weekly.destination) {
            WeeklyScreen()
        }

        composable(NavigationDirection.Cities.destination) {
            CitiesScreen()
        }
    }
}