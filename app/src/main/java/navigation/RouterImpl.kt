package navigation

class RouterImpl(private var navigationDelegate: NavigationDelegate) : Router {

    override fun showCitiesScreen() {
        navigationDelegate.navigate(NavigationEvent.Cities)
    }

    override fun showHomeScreen() {
        navigationDelegate.navigate(NavigationEvent.Home)
    }

    override fun showWeeklyScreen() {
        navigationDelegate.navigate(NavigationEvent.Weekly)
    }

    override fun showSettingsScreen() {
        navigationDelegate.navigate(NavigationEvent.Settings)
    }
}