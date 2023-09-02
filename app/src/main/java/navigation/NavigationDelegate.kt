package navigation

import kotlinx.coroutines.flow.SharedFlow

interface NavigationDelegate {

    fun navigate(navigationCommand: NavigationCommand)

    fun getNavigationEvents(): SharedFlow<NavigationCommand>
}