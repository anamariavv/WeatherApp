package navigation

import kotlinx.coroutines.flow.SharedFlow
import navigation.component.NavigationDirection

interface NavigationDelegate {

    fun navigate(navigationDirection: NavigationDirection)

    fun getNavigationEvents(): SharedFlow<NavigationDirection>
}