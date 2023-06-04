package navigation

import kotlinx.coroutines.flow.SharedFlow

interface NavigationDelegate {

    fun navigate(navigationEvent: NavigationEvent)

    fun getNavigationEvents(): SharedFlow<NavigationEvent>
}