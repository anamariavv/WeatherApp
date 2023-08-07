package navigation.component

sealed class NavigationDirection(val destination: String) {
    object Home : NavigationDirection(destination = "route_home")
    object Weekly : NavigationDirection(destination = "route_weekly")
    object Settings : NavigationDirection(destination = "route_settings")
    object Cities : NavigationDirection(destination = "route_cities")
}