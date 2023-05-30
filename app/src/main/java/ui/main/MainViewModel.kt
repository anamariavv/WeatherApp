package ui.main

import com.example.weatherapp.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ui.common.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel() {

    private val _topAppBarState = MutableStateFlow(TopAppBarState(isVisible = true, titleTextId = R.string.top_app_bar_title_placeholder))
    val topAppBarState: StateFlow<TopAppBarState> = _topAppBarState

    fun onCityActionButtonClicked() {
        router.showCitiesScreen()
    }

}