package repository

import model.forecast.CurrentConditions
import model.forecast.Forecast
import model.forecast.HourlyForecast

interface ForecastRepository {

	suspend fun getDailyForecast(locationKey: String): Forecast

	suspend fun getCurrentConditions(locationKey: String): CurrentConditions

	suspend fun getWeeklyForecast(locationKey: String): Forecast

	suspend fun getTwelveHourForecast(locationKey: String): HourlyForecast
}