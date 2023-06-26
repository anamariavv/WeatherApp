package navigation.impl

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

    override fun showWeeklyScreen() {
        navigate(NavigationDirection.Weekly)
    }

    override fun showSettingsScreen() {
        navigate(NavigationDirection.Settings)
    }

    override fun navigate(navigationDirection: NavigationDirection) {
        navigationDelegate.navigate(navigationDirection)
    }
}