package ui.cities

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ui.base.BaseViewModel
import utils.empty
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(): BaseViewModel() {
    private val _searchBarState = MutableStateFlow(SearchBarState(queryText = String.empty(), isActive = true, isEnabled = true))
    val searchBarState = _searchBarState.asStateFlow()

    fun updateQueryAndSearch(queryText: String) {
        _searchBarState.update { it.copy(queryText = queryText, isActive = true, isEnabled = true) }
    }

    fun performSearch(queryText: String) {
        runSuspend{ performSearchInternal(queryText) }
    }

    private suspend fun performSearchInternal(queryText: String) {
        //TODO call use case
    }

}