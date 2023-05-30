package navigation

class RouterImpl(var navigationDelegate: NavigationDelegate) : Router {

    override fun showCitiesScreen() {
        navigationDelegate.navigateToCitiesScreen()
    }
}