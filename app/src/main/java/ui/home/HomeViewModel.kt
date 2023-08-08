package ui.home

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
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val getFavouriteCitiesUseCase: GetFavouriteCitiesUseCase) : BaseViewModel() {

	private val _dropdownState = MutableStateFlow(DropdownState(isExpanded = false, list = listOf(), selectedIndex = 0, selectedValue = null))
	val dropdownState = _dropdownState.asStateFlow()

	init {
		runSuspend { getFavouriteCities() }
	}

	private suspend fun getFavouriteCities() {
		getFavouriteCitiesUseCase().onFinished(this::getFavouriteCitiesSuccess, this::handleErrors)
	}

	private fun getFavouriteCitiesSuccess(response: GetFavouriteCitiesUseCaseResponse) {
		_dropdownState.update { it.copy(list = response.list, selectedIndex = 0, selectedValue = response.list[0]) }
	}

	private fun handleErrors(errorData: ErrorData) {
		when (errorData.errorType) {
			GetFavouriteCitiesError.GET_FAVOURITES_ERROR -> showError(CityScreenMessages.AddFavouriteCityError)
		}
	}

	fun closeDropdown() {
		_dropdownState.update { it.copy(isExpanded = false) }
	}

	fun toggleDropdownExpanded(isExpanded: Boolean) {
		_dropdownState.update { it.copy(isExpanded = !isExpanded) }
	}

	fun onItemSelected(city: City, index: Int) {
		_dropdownState.update { it.copy(isExpanded = false, selectedIndex = index, selectedValue = city) }
	}

	private suspend fun getDailyForecast(city: City) {

	}

}