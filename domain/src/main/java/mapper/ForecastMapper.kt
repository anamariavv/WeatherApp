package mapper

import model.forecast.CurrentConditions
import model.forecast.Forecast
import model.forecast.HourlyForecast
import model.network.forecast.current.ApiCurrentConditionsItem
import model.network.forecast.daily.ApiForecast
import model.network.forecast.hourly.ApiHourlyForecast

interface ForecastMapper {

	fun toDailyForecastInfo(apiForecast: ApiForecast): Forecast

	fun toCurrentConditionsMetric(apiItem: ApiCurrentConditionsItem): CurrentConditions

	fun toCurrentConditionsImperial(apiItem: ApiCurrentConditionsItem): CurrentConditions

	fun toHourlyForecast(apiForecast: ApiHourlyForecast) : HourlyForecast
}