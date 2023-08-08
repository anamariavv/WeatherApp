package repository

import model.forecast.Forecast

interface ForecastRepository {

	suspend fun getDailyForecast(locationKey: String): Forecast
}