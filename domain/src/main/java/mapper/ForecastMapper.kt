package mapper

import model.forecast.Forecast
import model.network.forecast.daily.ApiForecast

interface ForecastMapper {

	suspend fun toDailyForecastInfo(apiForecast: ApiForecast): Forecast
}