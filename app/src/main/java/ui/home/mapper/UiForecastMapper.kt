package ui.home.mapper

import model.forecast.CurrentConditions
import model.forecast.HourlyForecast
import ui.home.model.UiCurrentConditions
import ui.home.model.UiHourlyForecast

interface UiForecastMapper {

	fun toUiHourlyForecast(forecast: HourlyForecast): UiHourlyForecast

	fun toUiCurrentConditions(item: CurrentConditions): UiCurrentConditions
}