package ui.main

import com.example.weatherapp.R
import config.Config
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import navigation.BottomNavItem
import ui.base.BaseViewModel
import ui.common.BottomNavBarState
import ui.common.TopAppBarState
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel() {

    private val _topAppBarState = MutableStateFlow(TopAppBarState(isVisible = true, titleTextId = R.string.top_app_bar_title_placeholder))
    val topAppBarState: StateFlow<TopAppBarState> = _topAppBarState

    private val _bottomNavBarState = MutableStateFlow(BottomNavBarState(isVisible = true, onItemClicked = this::onBottomNavItemClicked,
        items = Config.BOTTOM_NAV_BAR_ITEMS, selectedItem = Config.BOTTOM_NAV_BAR_ITEMS[0]
    ))
    val bottomNavBarState: StateFlow<BottomNavBarState> = _bottomNavBarState

    fun onCityActionButtonClicked() {
        router.showCitiesScreen()
    }

    private fun onBottomNavItemClicked(item: BottomNavItem) {
        router.navigate(item.direction)
        
        _bottomNavBarState.update { it.copy(selectedItem = item) }
    }
}