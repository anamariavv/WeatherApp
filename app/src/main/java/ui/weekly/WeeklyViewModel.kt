package ui.weekly

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import model.common.ErrorData
import ui.base.BaseViewModel
import ui.common.mapper.UiForecastMapper
import ui.weekly.model.UiWeeklyForecast
import ui.weekly.model.WeeklyScreenMessages
import usecase.forecast.GetWeeklyForecastUseCase
import usecase.forecast.GetWeeklyForecastUseCase.GetWeeklyForecastError
import usecase.forecast.GetWeeklyForecastUseCase.GetWeeklyForecastUseCaseResponse
import javax.inject.Inject

@HiltViewModel
class WeeklyViewModel @Inject constructor(
	savedStateHandle: SavedStateHandle,
	private val getWeeklyForecastUseCase: GetWeeklyForecastUseCase,
	private val forecastMapper: UiForecastMapper
) : BaseViewModel() {

	private val locationKey: String = checkNotNull(savedStateHandle["locationKey"])
	private val _weeklyForecastState = MutableStateFlow(UiWeeklyForecast(items = listOf()))
	val weeklyForecastState = _weeklyForecastState.asStateFlow()

	init {
		showScreenLoading()
		runSuspend { initScreenData() }
	}

	private suspend fun initScreenData() {
		getWeeklyForecastUseCase(locationKey).onFinished(this::getWeeklyForecastSuccess, this::getWeeklyForecastError)
	}

	private fun getWeeklyForecastSuccess(response: GetWeeklyForecastUseCaseResponse) {
		_weeklyForecastState.value = forecastMapper.toWeeklyForecast(response.forecast)
		showScreenContent()
	}

	private fun getWeeklyForecastError(errorData: ErrorData) {
		when (errorData.errorType) {
			GetWeeklyForecastError.GET_WEEKLY_FORECAST_ERROR -> showErrorDialog(WeeklyScreenMessages.GetWeeklyForecastError)
		}

		showScreenNoContent()
	}
}