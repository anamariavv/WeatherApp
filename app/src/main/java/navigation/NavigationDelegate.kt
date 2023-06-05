package navigation

import kotlinx.coroutines.flow.SharedFlow

interface NavigationDelegate {

    fun navigate(navigationDirection: NavigationDirection)

    fun getNavigationEvents(): SharedFlow<NavigationDirection>
}