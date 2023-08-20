package ui.common.mapper

import model.forecast.CurrentConditions
import model.forecast.Forecast
import model.forecast.HourlyForecast
import ui.home.model.UiCurrentConditions
import ui.home.model.UiHourlyForecast
import ui.weekly.model.UiWeeklyForecast

interface UiForecastMapper {

	fun toUiHourlyForecast(forecast: HourlyForecast): UiHourlyForecast

	fun toUiCurrentConditions(item: CurrentConditions): UiCurrentConditions

	fun toWeeklyForecast(forecast: Forecast): UiWeeklyForecast
}