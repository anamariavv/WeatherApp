package ui.cities

import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import model.City
import ui.base.BaseViewModel
import usecase.cities.QueryCitiesUseCase
import utils.empty
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(
    private val queryCitiesUseCase: QueryCitiesUseCase
) : BaseViewModel() {

    private val _searchBarState = MutableStateFlow(
        SearchBarState(
            queryText = String.empty(),
            isActive = false,
            isEnabled = true,
            queryResult = listOf()
        )
    )
    val searchBarState = _searchBarState.asStateFlow()

    fun updateQueryAndSearch(queryText: String) {
        _searchBarState.update { it.copy(queryText = queryText, isActive = true, isEnabled = true) }
        performSearch(queryText)
    }

    fun updateCurrentCity(city: City) {
        //TODO: save city in shared prefs
        Log.d("cityy", city.localizedName)
    }

    fun onActiveChange(isActive: Boolean) {
        _searchBarState.update { it.copy(isActive = isActive) }
    }

    fun performSearch(queryText: String) {
        runSuspend { performSearchInternal(queryText) }
    }

    private suspend fun performSearchInternal(queryText: String) {
        queryCitiesUseCase(queryText).onSuccess(this::searchSuccess)
    }

    private fun searchSuccess(response: QueryCitiesUseCase.QueryCitiesUseCaseResponse) {
        response.autocompleteCities.forEach { Log.d("city", it.toString()) }
        _searchBarState.update { it.copy(queryResult = response.autocompleteCities) }
    }

}