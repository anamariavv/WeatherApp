package repository

import model.forecast.CurrentConditions
import model.forecast.Forecast

interface ForecastRepository {

	suspend fun getDailyForecast(locationKey: String): Forecast

	suspend fun getCurrentConditions(locationKey: String): CurrentConditions

	suspend fun getWeeklyForecast(locationKey: String): Forecast
}