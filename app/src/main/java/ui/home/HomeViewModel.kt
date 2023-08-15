package ui.home

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import model.city.City
import model.common.ErrorData
import ui.base.BaseViewModel
import ui.cities.model.CityScreenMessages
import ui.home.mapper.UiForecastMapper
import ui.home.model.DropdownState
import ui.home.model.UiCurrentConditions
import ui.home.model.UiHourlyForecast
import usecase.city.GetFavouriteCitiesUseCase
import usecase.city.GetFavouriteCitiesUseCase.GetFavouriteCitiesUseCaseResponse
import usecase.city.GetFavouriteCitiesUseCase.GetFavouriteCitiesError
import usecase.forecast.GetCurrentConditionsUseCase
import usecase.forecast.GetDailyForecastUseCase
import usecase.forecast.GetDailyForecastUseCase.GetDailyForecastError
import usecase.forecast.GetDailyForecastUseCase.GetDailyForecastUseCaseResponse
import usecase.forecast.GetTwelveHourForecastUseCase
import usecase.forecast.GetTwelveHourForecastUseCase.GetTwelveHourForecastUseCaseResponse
import usecase.location.GetCurrentCityUseCase
import usecase.location.GetCurrentCityUseCase.GetCurrentCityUseCaseResponse
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
	private val getFavouriteCitiesUseCase: GetFavouriteCitiesUseCase,
	private val getDailyForecastUseCase: GetDailyForecastUseCase,
	private val getTwelveHourForecastUseCase: GetTwelveHourForecastUseCase,
	private val getCurrentConditionsUseCase: GetCurrentConditionsUseCase,
	private val getCurrentCityUseCase: GetCurrentCityUseCase,
	private val uiForecastMapper: UiForecastMapper
) : BaseViewModel() {

	private val _dropdownState = MutableStateFlow(DropdownState(isExpanded = false, list = listOf(), selectedIndex = 0, selectedValue = null))
	val dropdownState = _dropdownState.asStateFlow()

	private val _hourlyForecastState = MutableStateFlow(UiHourlyForecast(hours = listOf()))
	val hourlyForecastState = _hourlyForecastState.asStateFlow()

	private val _currentConditionsState = MutableStateFlow(UiCurrentConditions())
	val currentConditionsState = _currentConditionsState.asStateFlow()

	init {
		//runSuspend { getFavouriteCities() }
		runSuspend { getTwelveHourForecast("120665") }
		runSuspend { getCurrentConditions("120665")}
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

	private fun getDailyForecastSuccess(response: GetDailyForecastUseCaseResponse) {

	}

	private suspend fun getCurrentConditions(locationKey: String) {
		getCurrentConditionsUseCase(locationKey).onFinished(this::getCurrentConditionsSuccess, this::handleErrors)
	}

	private fun getCurrentConditionsSuccess(response: GetCurrentConditionsUseCase.GetCurrentConditionsUseCaseResponse) {
		_currentConditionsState.value = uiForecastMapper.toUiCurrentConditions(response.currentConditions)
	}

	private suspend fun getTwelveHourForecast(locationKey: String) {
		getTwelveHourForecastUseCase(locationKey).onFinished(this::getTwelveHourForecastSuccess, this::handleErrors)
	}

	private fun getTwelveHourForecastSuccess(response: GetTwelveHourForecastUseCaseResponse) {
		_hourlyForecastState.value = uiForecastMapper.toUiHourlyForecast(response.forecast)
	}

	private fun handleErrors(errorData: ErrorData) {
		when (errorData.errorType) {
			GetFavouriteCitiesError.GET_FAVOURITES_ERROR -> showError(CityScreenMessages.AddFavouriteCityError)
			GetDailyForecastError.GET_FORECAST_ERROR -> showError(CityScreenMessages.AddFavouriteCityError)
		}
	}
}