package navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ui.home.HomeScreen
import ui.settings.SettingsScreen
import ui.weekly.WeeklyScreen

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = "route_home") {
        composable(BottomNavItem.Home.route) {
            HomeScreen()
        }

        composable(BottomNavItem.Settings.route) {
            SettingsScreen()
        }

        composable(BottomNavItem.Weekly.route) {
            WeeklyScreen()
        }
    }
}