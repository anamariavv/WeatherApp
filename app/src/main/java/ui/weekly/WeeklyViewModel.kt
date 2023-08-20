package ui.weekly

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import mapper.ForecastMapper
import model.common.ErrorData
import ui.base.BaseViewModel
import ui.common.mapper.UiForecastMapper
import ui.weekly.model.UiWeeklyForecast
import usecase.forecast.GetWeeklyForecastUseCase
import usecase.forecast.GetWeeklyForecastUseCase.GetWeeklyForecastUseCaseResponse
import javax.inject.Inject

@HiltViewModel
class WeeklyViewModel @Inject constructor(
	private val savedStateHandle: SavedStateHandle,
	private val getWeeklyForecastUseCase: GetWeeklyForecastUseCase,
	private val forecastMapper: UiForecastMapper
) : BaseViewModel() {

	private val locationKey: String = checkNotNull(savedStateHandle["locationKey"])
	private val _weeklyForecastState = MutableStateFlow(UiWeeklyForecast(items = listOf()))
	val weeklyForecastState = _weeklyForecastState.asStateFlow()

	 init {
		 runSuspend { initScreenData() }
	 }

	private suspend fun initScreenData() {
		getWeeklyForecastUseCase(locationKey).onFinished(this::getWeeklyForecastSuccess, this::getWeeklyForecastError)
	}

	private fun getWeeklyForecastSuccess(response: GetWeeklyForecastUseCaseResponse) {
		_weeklyForecastState.value = forecastMapper.toWeeklyForecast(response.forecast)
	}

	private fun getWeeklyForecastError(errorData: ErrorData) {
		//todo
	}
}