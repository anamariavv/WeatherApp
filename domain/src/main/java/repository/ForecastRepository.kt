package repository

import model.forecast.DailyForecastInfo

interface ForecastRepository {

	suspend fun getDailyForecast(locationKey: String): DailyForecastInfo
}