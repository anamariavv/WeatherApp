package navigation

sealed class NavigationEvent(val destination: String) {
    object Home : NavigationEvent(destination = "route_home")
    object Weekly : NavigationEvent(destination = "route_weekly")
    object Settings : NavigationEvent(destination = "route_settings")
    object Cities : NavigationEvent(destination = "route_cities")
}