package navigation

import navigation.component.NavigationDirection

interface Router {

    fun showCitiesScreen()

    fun showHomeScreen()

    fun showWeeklyScreen()

    fun showSettingsScreen()

    fun navigate(navigationDirection: NavigationDirection)
}