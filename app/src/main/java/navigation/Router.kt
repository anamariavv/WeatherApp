package navigation

interface Router {

    fun showCitiesScreen()

    fun showHomeScreen()

    fun showSettingsScreen()

    fun navigate(navigationCommand: NavigationCommand)

    fun navigateToWeeklyScreen(locationKey: String)
}