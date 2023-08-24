package ui.home

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import model.city.City
import model.common.ErrorData
import ui.base.BaseViewModel
import ui.common.model.CommonMessages
import ui.common.mapper.UiForecastMapper
import ui.home.model.DropdownState
import ui.home.model.HomeScreenMessages
import ui.home.model.UiCurrentConditions
import ui.home.model.UiHourlyForecast
import usecase.city.AddFavouriteCityUseCase
import usecase.city.GetFavouriteCitiesUseCase
import usecase.city.GetFavouriteCitiesUseCase.GetFavouriteCitiesUseCaseResponse
import usecase.city.GetFavouriteCitiesUseCase.GetFavouriteCitiesError
import usecase.city.GetSelectedCityLocationKeyUseCase
import usecase.city.GetSelectedCityLocationKeyUseCase.GetSelectedCityLocationKeyUseCaseResponse
import usecase.city.SetSelectedCityLocationKeyUseCase
import usecase.forecast.GetCurrentConditionsUseCase
import usecase.forecast.GetCurrentConditionsUseCase.GetCurrentConditionsUseCaseResponse
import usecase.forecast.GetCurrentConditionsUseCase.GetCurrentConditionsError
import usecase.forecast.GetTwelveHourForecastUseCase
import usecase.forecast.GetTwelveHourForecastUseCase.GetTwelveHourForecastUseCaseResponse
import usecase.forecast.GetTwelveHourForecastUseCase.GetTwelveHourForecastError
import usecase.location.GetCurrentCityUseCase
import usecase.location.GetCurrentCityUseCase.GetCurrentCityUseCaseResponse
import usecase.location.GetCurrentCityUseCase.GetCurrentCityUseCaseError
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
	private val getFavouriteCitiesUseCase: GetFavouriteCitiesUseCase,
	private val getTwelveHourForecastUseCase: GetTwelveHourForecastUseCase,
	private val getCurrentConditionsUseCase: GetCurrentConditionsUseCase,
	private val getCurrentCityUseCase: GetCurrentCityUseCase,
	private val setSelectedCityLocationKeyUseCase: SetSelectedCityLocationKeyUseCase,
	private val getSelectedCityLocationKeyUseCase: GetSelectedCityLocationKeyUseCase,
	private val addFavouriteCityUseCase: AddFavouriteCityUseCase,
	private val uiForecastMapper: UiForecastMapper,
) : BaseViewModel() {
	private lateinit var currentLocationKey: String

	private val _dropdownState = MutableStateFlow(DropdownState(isExpanded = false, list = listOf(), selectedIndex = 0, selectedValue = null))
	val dropdownState = _dropdownState.asStateFlow()

	private val _hourlyForecastState = MutableStateFlow(UiHourlyForecast(hours = listOf()))
	val hourlyForecastState = _hourlyForecastState.asStateFlow()

	private val _currentConditionsState = MutableStateFlow(UiCurrentConditions())
	val currentConditionsState = _currentConditionsState.asStateFlow()

	init {
		showScreenLoading()
		runSuspend { getFavouriteCities() }
	}

	private suspend fun getFavouriteCities() {
		getFavouriteCitiesUseCase().onFinished(this::getFavouriteCitiesSuccess, this::handleErrors)
	}

	private fun getFavouriteCitiesSuccess(response: GetFavouriteCitiesUseCaseResponse) {
		if (response.list.isNotEmpty()) {
			_dropdownState.update { it.copy(list = response.list, selectedIndex = 0, selectedValue = response.list[0]) }
			runSuspend { getSelectedCityLocationKey() }
		} else {
			runSuspend { getCurrentCity() }
		}
	}

	private suspend fun getSelectedCityLocationKey() {
		getSelectedCityLocationKeyUseCase().onFinished(this::getSelectedCityLocationKeySuccess, this::handleErrors)
	}

	private fun getSelectedCityLocationKeySuccess(response: GetSelectedCityLocationKeyUseCaseResponse) {
		val list = _dropdownState.value.list
		var selectedIndex = 0
		var selectedCity = list[0]

		list.forEachIndexed { index, it ->
			if (it.locationKey == response.locationKey) {
				selectedIndex = index
				selectedCity = it
			}
		}

		currentLocationKey = response.locationKey

		_dropdownState.update { it.copy(selectedIndex = selectedIndex, selectedValue = selectedCity) }
		updateForecastInformation(response.locationKey)
	}

	private suspend fun getCurrentCity() {
		getCurrentCityUseCase().onFinished(this::getCurrentCitySuccess, this::handleErrors)
	}

	private fun getCurrentCitySuccess(response: GetCurrentCityUseCaseResponse) {
		currentLocationKey = response.city.locationKey
		_dropdownState.update { it.copy(list = listOf(response.city), selectedIndex = 0, selectedValue = response.city) }
		runSuspend { updateForecastInformation(response.city.locationKey) }
		runSuspend { setSelectedCityLocationKey(response.city.locationKey) }
		runSuspend { addFavouriteCityUseCase(response.city) }
		showInfo(HomeScreenMessages.CityListInfo)
	}

	fun closeDropdown() {
		_dropdownState.update { it.copy(isExpanded = false) }
	}

	fun toggleDropdownExpanded(isExpanded: Boolean) {
		_dropdownState.update { it.copy(isExpanded = !isExpanded) }
	}

	fun onItemSelected(city: City, index: Int) {
		_dropdownState.update { it.copy(isExpanded = false, selectedIndex = index, selectedValue = city) }
		runSuspend { updateForecastInformation(city.locationKey) }
		runSuspend { setSelectedCityLocationKey(city.locationKey) }
		currentLocationKey = city.locationKey
	}

	private suspend fun setSelectedCityLocationKey(locationKey: String) {
		setSelectedCityLocationKeyUseCase(locationKey).onError(this::handleErrors)
	}

	private fun updateForecastInformation(locationKey: String) {
		runSuspend { getCurrentConditions(locationKey) }
	}

	private suspend fun getCurrentConditions(locationKey: String) {
		getCurrentConditionsUseCase(locationKey).onFinished(this::getCurrentConditionsSuccess, this::handleErrors)
	}

	private fun getCurrentConditionsSuccess(response: GetCurrentConditionsUseCaseResponse) {
		_currentConditionsState.value = uiForecastMapper.toUiCurrentConditions(response.currentConditions)
		runSuspend { getTwelveHourForecast(currentLocationKey) }
	}

	private suspend fun getTwelveHourForecast(locationKey: String) {
		getTwelveHourForecastUseCase(locationKey).onFinished(this::getTwelveHourForecastSuccess, this::handleErrors)
	}

	private fun getTwelveHourForecastSuccess(response: GetTwelveHourForecastUseCaseResponse) {
		_hourlyForecastState.value = uiForecastMapper.toUiHourlyForecast(response.forecast)
		showScreenContent()
	}

	fun navigateToWeeklyScreen() {
		router.navigateToWeeklyScreen(currentLocationKey)
	}

	fun onNotificationPermissionResult(isGranted: Boolean) {
		if (!isGranted) {
			showInfo(HomeScreenMessages.NotificationPermissionInfo)
		}
	}

	private fun handleErrors(errorData: ErrorData) {
		when (errorData.errorType) {
			GetFavouriteCitiesError.GET_FAVOURITES_ERROR -> showError(HomeScreenMessages.GetFavouriteCitiesError)
			GetTwelveHourForecastError.GET_TWELVE_HOUR_FORECAST_ERROR -> showError(HomeScreenMessages.GetForecastError)
			GetCurrentConditionsError.GET_CONDITIONS_ERROR -> showError(HomeScreenMessages.GetForecastError)
			GetCurrentCityUseCaseError.GET_LOCATION_ERROR -> showError(CommonMessages.GetLocationError)
		}

		showScreenNoContent()
	}
}