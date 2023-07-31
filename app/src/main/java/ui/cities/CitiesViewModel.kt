package ui.cities

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import model.City
import model.common.ErrorData
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

    fun toggleFavouriteCity(city: City, cityIndex: Int) {
         _searchBarState.update {
             it.copy(queryResult = _searchBarState.value.queryResult.mapIndexed { index, city ->
                 if (index == cityIndex) city.copy(isFavourite = !city.isFavourite) else city
             })
         }

        runSuspend { toggleFavouriteCityInternal(city) }
    }

    private suspend fun toggleFavouriteCityInternal(city: City) {
        toggleFavouriteCityUseCase(city).onFinished(this::toggleFavouriteCitySuccess, this::toggleFavouriteCityError)
    }

    private fun toggleFavouriteCitySuccess() {
        //move ui update here
    }

    private fun toggleFavouriteCityError(errorData: ErrorData) {
        /* when(errorData.errorType) {
            ToggleFavouriteCityUseCase.ToggleFavouriteCitiesError.ADD_FAVOURITE_CITY_ERROR -> _,
            ToggleFavouriteCityUseCase.ToggleFavouriteCitiesError.REMOVE_FAVOURITE_CITY_ERROR -> _
        }*/
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