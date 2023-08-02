package ui.cities

import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import model.City
import model.common.ErrorData
import ui.base.BaseViewModel
import usecase.city.GetFavouriteCitiesUseCase.GetFavouriteCitiesUseCaseResponse
import usecase.city.GetFavouriteCitiesUseCase
import usecase.city.QueryCitiesUseCase
import usecase.city.QueryCitiesUseCase.QueryCitiesUseCaseResponse
import usecase.location.GetCurrentCityUseCase.GetCurrentCityUseCaseResponse
import usecase.city.ToggleFavouriteCityUseCase
import usecase.location.GetCurrentCityUseCase
import utils.empty
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(
	private val queryCitiesUseCase: QueryCitiesUseCase,
	private val toggleFavouriteCityUseCase: ToggleFavouriteCityUseCase,
	private val getFavouriteCitiesUseCase: GetFavouriteCitiesUseCase,
	private val getCurrentCityUseCase: GetCurrentCityUseCase
) : BaseViewModel() {

	private val _searchBarState = MutableStateFlow(
		SearchBarState(queryText = String.empty(), isActive = false, isEnabled = true, queryResult = listOf())
	)
	val searchBarState = _searchBarState.asStateFlow()

	private val _favouriteCityListState = MutableStateFlow(FavouriteCityListState(cities = listOf()))
	val favouriteCityListState = _favouriteCityListState.asStateFlow()

	init {
		getFavouriteCityList()
	}

	private fun getFavouriteCityList() {
		runSuspend { getFavouriteCityListInternal() }
	}

	private suspend fun getFavouriteCityListInternal() {
		getFavouriteCitiesUseCase().onFinished(this::getFavouriteCityListSuccess, this::handleErrors)
	}

	private fun getFavouriteCityListSuccess(response: GetFavouriteCitiesUseCaseResponse) {
		_favouriteCityListState.update { it.copy(cities = response.list) }
	}

	fun onActiveChange(isActive: Boolean) {
		_searchBarState.update { it.copy(isActive = isActive) }
	}

	fun onSearchbarCloseButtonClick() {
		_searchBarState.update { it.copy(queryText = String.empty(), isActive = false) }
	}

	fun updateQueryAndSearch(queryText: String) {
		_searchBarState.update { it.copy(queryText = queryText, isActive = true, isEnabled = true) }
		performSearch(queryText)
	}

	fun performSearch(queryText: String) {
		runSuspend { performSearchInternal(queryText) }
	}

	private suspend fun performSearchInternal(queryText: String) {
		queryCitiesUseCase(queryText).onFinished(this::searchSuccess, this::handleErrors)
	}

	private fun searchSuccess(response: QueryCitiesUseCaseResponse) {
		_searchBarState.update { it.copy(queryResult = response.autocompleteCities) }
	}

	fun toggleFavouriteCity(city: City, cityIndex: Int) {
		updateFavouriteCityListState(cityIndex)
		runSuspend { toggleFavouriteCityInternal(city) }
	}

	private fun updateFavouriteCityListState(cityIndex: Int) {
		_searchBarState.update {
			it.copy(queryResult = _searchBarState.value.queryResult.mapIndexed { index, city ->
				if (index == cityIndex) city.copy(isFavourite = !city.isFavourite) else city
			})
		}
	}

	private suspend fun toggleFavouriteCityInternal(city: City) {
		toggleFavouriteCityUseCase(city).onFinished(this::toggleFavouriteCitySuccess, this::handleErrors)
	}

	private fun toggleFavouriteCitySuccess() {
		getFavouriteCityList()
	}

	fun onLocationPermissionRequestResult(isGranted: Boolean) {
		if (isGranted) {
			getCurrentCity()
		} else {
			showInfo("Location permission is necessary in order to use this functionality. Please enable it in app settings.")
		}
	}

	fun getCurrentCity() {
		runSuspend { getCurrentCityInternal() }
	}

	private suspend fun getCurrentCityInternal() {
		getCurrentCityUseCase().onFinished(this::onGetCurrentCitySuccess, this::handleErrors)
	}

	private fun onGetCurrentCitySuccess(response: GetCurrentCityUseCaseResponse) {
		Log.d("currentLocation!:", response.city.toString())
	}

	private fun handleErrors(errorData: ErrorData) {
		when (errorData.errorType) {
			ToggleFavouriteCityUseCase.ToggleFavouriteCitiesError.ADD_FAVOURITE_CITY_ERROR -> showError("A")
			ToggleFavouriteCityUseCase.ToggleFavouriteCitiesError.REMOVE_FAVOURITE_CITY_ERROR -> showError("R")
			GetFavouriteCitiesUseCase.GetFavouriteCitiesError.ERROR_GETTING_LIST -> showError("G")
			QueryCitiesUseCase.QueryCitiesError.GET_CITY_LIST_ERROR -> showError("E")
			GetCurrentCityUseCase.GetCurrentCityUseCaseError.ERROR_GETTING_LOCATION -> showError(errorData.throwable.localizedMessage)
		}
	}
}