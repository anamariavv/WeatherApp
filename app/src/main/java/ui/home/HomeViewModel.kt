package ui.home

import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import model.city.City
import model.common.ErrorData
import ui.base.BaseViewModel
import ui.cities.model.CityScreenMessages
import ui.home.model.DropdownState
import usecase.city.GetFavouriteCitiesUseCase
import usecase.city.GetFavouriteCitiesUseCase.GetFavouriteCitiesUseCaseResponse
import usecase.city.GetFavouriteCitiesUseCase.GetFavouriteCitiesError
import usecase.forecast.GetDailyForecastUseCase
import usecase.forecast.GetDailyForecastUseCase.GetDailyForecastError
import usecase.forecast.GetDailyForecastUseCase.GetDailyForecastUseCaeResponse
import usecase.location.GetCurrentCityUseCase
import usecase.location.GetCurrentCityUseCase.GetCurrentCityUseCaseResponse
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
	val getFavouriteCitiesUseCase: GetFavouriteCitiesUseCase,
	val getDailyForecastUseCase: GetDailyForecastUseCase,
	val getCurrentCityUseCase: GetCurrentCityUseCase
) : BaseViewModel() {

	private val _dropdownState = MutableStateFlow(DropdownState(isExpanded = false, list = listOf(), selectedIndex = 0, selectedValue = null))
	val dropdownState = _dropdownState.asStateFlow()

	init {
		runSuspend { getFavouriteCities() }
	}

	private suspend fun getFavouriteCities() {
		getFavouriteCitiesUseCase().onFinished(this::getFavouriteCitiesSuccess, this::handleErrors)
	}

	private fun getFavouriteCitiesSuccess(response: GetFavouriteCitiesUseCaseResponse) {
		if (response.list.isNotEmpty()) {
			runSuspend { getDailyForecast(response.list[0].locationKey) }
			_dropdownState.update { it.copy(list = response.list, selectedIndex = 0, selectedValue = response.list[0]) }
		} else {
			runSuspend { getCurrentCity() }
		}
	}

	private suspend fun getCurrentCity() {
		getCurrentCityUseCase().onFinished(this::getCurrentCitySuccess, this::handleErrors)
	}

	private fun getCurrentCitySuccess(response: GetCurrentCityUseCaseResponse) {
		_dropdownState.update { it.copy(list = listOf(response.city), selectedIndex = 0, selectedValue = response.city) }
		runSuspend { getDailyForecast(response.city.locationKey) }
	}

	fun closeDropdown() {
		_dropdownState.update { it.copy(isExpanded = false) }
	}

	fun toggleDropdownExpanded(isExpanded: Boolean) {
		_dropdownState.update { it.copy(isExpanded = !isExpanded) }
	}

	fun onItemSelected(city: City, index: Int) {
		_dropdownState.update { it.copy(isExpanded = false, selectedIndex = index, selectedValue = city) }
		//save choice to database
	}

	private suspend fun getDailyForecast(locationKey: String) {
		getDailyForecastUseCase(locationKey).onFinished(this::getDailyForecastSuccess, this::handleErrors)
	}

	private fun getDailyForecastSuccess(response: GetDailyForecastUseCaeResponse) {
		Log.d("forecast:R", response.toString())
	}

	private fun handleErrors(errorData: ErrorData) {
		when (errorData.errorType) {
			GetFavouriteCitiesError.GET_FAVOURITES_ERROR -> showError(CityScreenMessages.AddFavouriteCityError)
			GetDailyForecastError.GET_FORECAST_ERROR -> showError(CityScreenMessages.AddFavouriteCityError)
		}
	}
}