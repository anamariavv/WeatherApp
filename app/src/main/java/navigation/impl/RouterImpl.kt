package navigation.impl

import navigation.NavigationCommand
import navigation.NavigationDelegate
import navigation.component.NavigationDirection
import navigation.Router

class RouterImpl(private var navigationDelegate: NavigationDelegate) : Router {

    override fun showCitiesScreen() {
        navigate(NavigationDirection.Cities)
    }

    override fun showHomeScreen() {
        navigate(NavigationDirection.Home)
    }

    override fun showSettingsScreen() {
        navigate(NavigationDirection.Settings)
    }

    override fun navigate(navigationCommand: NavigationCommand) {
        navigationDelegate.navigate(navigationCommand)
    }

    override fun navigateToWeeklyScreen(locationKey: String) {
        navigate(NavigationDirection.Weekly.createRouteWithArgs(locationKey))
    }
}