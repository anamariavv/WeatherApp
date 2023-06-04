package navigation

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class NavigationDelegateImpl : NavigationDelegate {

    private var _navigationFlow = MutableSharedFlow<NavigationEvent>()
    private val navigationFlow: SharedFlow<NavigationEvent> = _navigationFlow

    override fun getNavigationEvents(): SharedFlow<NavigationEvent> {
        return navigationFlow
    }

   override fun navigate(navigationEvent: NavigationEvent) {
        _navigationFlow.tryEmit(navigationEvent)
   }
}