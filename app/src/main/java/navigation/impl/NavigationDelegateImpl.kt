package navigation.impl

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import navigation.NavigationCommand
import navigation.NavigationDelegate

class NavigationDelegateImpl : NavigationDelegate {

    private var _navigationFlow = MutableSharedFlow<NavigationCommand>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    private val navigationFlow: SharedFlow<NavigationCommand> = _navigationFlow

    override fun getNavigationEvents(): SharedFlow<NavigationCommand> {
        return navigationFlow
    }

   override fun navigate(navigationCommand: NavigationCommand) {
        _navigationFlow.tryEmit(navigationCommand)
   }
}