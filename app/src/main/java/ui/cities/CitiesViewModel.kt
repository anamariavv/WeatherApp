package ui.cities

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import model.City
import model.common.ErrorData
import ui.base.BaseViewModel
import ui.cities.model.CityScreenMessages
import ui.cities.model.FavouriteCityListState
import ui.cities.model.SearchBarState
import usecase.city.GetFavouriteCitiesUseCase.GetFavouriteCitiesUseCaseResponse
import usecase.city.GetFavouriteCitiesUseCase
import usecase.city.GetFavouriteCitiesUseCase.GetFavouriteCitiesError
import usecase.city.QueryCitiesUseCase
import usecase.city.QueryCitiesUseCase.QueryCitiesError
import usecase.city.QueryCitiesUseCase.QueryCitiesUseCaseResponse
import usecase.city.RemoveFavouriteCityUseCase
import usecase.city.RemoveFavouriteCityUseCase.RemoveFavouriteCityError
import usecase.location.GetCurrentCityUseCase.GetCurrentCityUseCaseResponse
import usecase.city.ToggleFavouriteCityUseCase
import usecase.city.ToggleFavouriteCityUseCase.ToggleFavouriteCitiesError
import usecase.location.GetCurrentCityUseCase
import usecase.location.GetCurrentCityUseCase.GetCurrentCityUseCaseError
import utils.empty
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(
		private val queryCitiesUseCase: QueryCitiesUseCase,
		private val toggleFavouriteCityUseCase: ToggleFavouriteCityUseCase,
		private val getFavouriteCitiesUseCase: GetFavouriteCitiesUseCase,
		private val getCurrentCityUseCase: GetCurrentCityUseCase,
		private val removeFavouriteCitiesUseCase: RemoveFavouriteCityUseCase
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
		toggleFavouriteCityUseCase(city).onFinished(this::getFavouriteCityList, this::handleErrors)
	}

	fun onLocationPermissionRequestResult(isGranted: Boolean) {
		if (isGranted) {
			getCurrentCity()
		} else {
			showInfo(CityScreenMessages.LocationPermissionNeeded)
		}
	}

	fun getCurrentCity() {
		showLoading()
		runSuspend { getCurrentCityInternal() }
	}

	private suspend fun getCurrentCityInternal() {
		getCurrentCityUseCase().onFinished(this::onGetCurrentCitySuccess, this::handleErrors)
	}

	private fun onGetCurrentCitySuccess(response: GetCurrentCityUseCaseResponse) {
		showInfo(CityScreenMessages.LocationResultInfo(response.city.localizedName, response.city.countryLocalizedName))
	}

	fun removeFavouriteCity(city: City, index: Int) {
		updateFavouriteCityListState(index)
		runSuspend { removeFavouriteCityInternal(city) }
	}

	private suspend fun removeFavouriteCityInternal(city: City) {
		removeFavouriteCitiesUseCase(city).onFinished(this::getFavouriteCityList, this::handleErrors)
	}

	private fun handleErrors(errorData: ErrorData) {
		//todo: better error titles
		when (errorData.errorType) {
			ToggleFavouriteCitiesError.ADD_FAVOURITE_CITY_ERROR -> showError(CityScreenMessages.AddFavouriteCityError)
			ToggleFavouriteCitiesError.REMOVE_FAVOURITE_CITY_ERROR -> showError(CityScreenMessages.RemoveFavouriteCityError)
			GetFavouriteCitiesError.GET_FAVOURITES_ERROR -> showError(CityScreenMessages.GetFavouritesError)
			QueryCitiesError.QUERY_CITIES_ERROR -> showError(CityScreenMessages.QueryCitiesError)
			GetCurrentCityUseCaseError.GET_LOCATION_ERROR -> showError(CityScreenMessages.GetLocationError)
		}
	}
}