package navigation.impl

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import navigation.NavigationDelegate
import navigation.component.NavigationDirection

class NavigationDelegateImpl : NavigationDelegate {

    private var _navigationFlow = MutableSharedFlow<NavigationDirection>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    private val navigationFlow: SharedFlow<NavigationDirection> = _navigationFlow

    override fun getNavigationEvents(): SharedFlow<NavigationDirection> {
        return navigationFlow
    }

   override fun navigate(navigationDirection: NavigationDirection) {
        _navigationFlow.tryEmit(navigationDirection)
   }
}