package mapper

import model.forecast.CurrentConditions
import model.forecast.Forecast
import model.forecast.HourlyForecast
import model.network.forecast.current.ApiCurrentConditionsItem
import model.network.forecast.daily.ApiForecast
import model.network.forecast.hourly.ApiHourlyForecast

interface ForecastMapper {

	suspend fun toDailyForecastInfo(apiForecast: ApiForecast): Forecast

	suspend fun toCurrentConditionsMetric(apiItem: ApiCurrentConditionsItem): CurrentConditions

	suspend fun toCurrentConditionsImperial(apiItem: ApiCurrentConditionsItem): CurrentConditions

	suspend fun toHourlyForecast(apiForecast: ApiHourlyForecast) : HourlyForecast
}