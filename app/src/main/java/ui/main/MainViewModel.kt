package ui.main

import config.Config
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import navigation.component.BottomNavItem
import ui.base.BaseViewModel
import ui.common.BottomNavBarState
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel() {

    private val _bottomNavBarState = MutableStateFlow(BottomNavBarState(isVisible = true, onItemClicked = this::onBottomNavItemClicked,
        items = Config.BOTTOM_NAV_BAR_ITEMS, selectedItem = Config.BOTTOM_NAV_BAR_ITEMS[0]
    ))
    val bottomNavBarState: StateFlow<BottomNavBarState> = _bottomNavBarState

    private fun onBottomNavItemClicked(item: BottomNavItem) {
        router.navigate(item.direction)
        
        _bottomNavBarState.update { it.copy(selectedItem = item) }
    }
}