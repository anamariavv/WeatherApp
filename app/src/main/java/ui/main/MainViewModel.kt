package ui.main

import com.example.weatherapp.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import navigation.BottomNavItem
import navigation.NavigationEvent
import ui.base.BaseViewModel
import ui.common.BottomNavBarState
import ui.common.TopAppBarState
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel() {

    private val _topAppBarState = MutableStateFlow(TopAppBarState(isVisible = true, titleTextId = R.string.top_app_bar_title_placeholder))
    val topAppBarState: StateFlow<TopAppBarState> = _topAppBarState

    private val _bottomNavBarState = MutableStateFlow(BottomNavBarState(isVisible = true, onItemClicked = this::onBottomNavItemClicked,
        items = listOf(BottomNavItem.Home, BottomNavItem.Weekly, BottomNavItem.Settings)))
    val bottomNavBarState: StateFlow<BottomNavBarState> = _bottomNavBarState

    fun onCityActionButtonClicked() {
        router.showCitiesScreen()
    }

    private fun onBottomNavItemClicked(navigationEvent: NavigationEvent) {
        //router.navigate(NavigationEvent)
    }
}