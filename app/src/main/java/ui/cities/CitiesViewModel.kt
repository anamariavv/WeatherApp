package ui.cities

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import model.City
import ui.base.BaseViewModel
import usecase.city.QueryCitiesUseCase
import usecase.city.ToggleFavouriteCityUseCase
import utils.empty
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(
    private val queryCitiesUseCase: QueryCitiesUseCase,
    private val toggleFavouriteCityUseCase: ToggleFavouriteCityUseCase
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
        //TODO: save city
    }

    fun toggleFavouriteCity(city: City) {
        runSuspend { toggleFavouriteCityInternal(city) }
    }

    private suspend fun toggleFavouriteCityInternal(city: City) {
        toggleFavouriteCityUseCase(city)
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
        _searchBarState.update { it.copy(queryResult = response.autocompleteCities) }
    }

}