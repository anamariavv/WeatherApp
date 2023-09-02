package repository.impl

import interactor.forecast.GetCurrentConditionsInteractor
import interactor.forecast.GetDailyForecastInteractor
import interactor.forecast.GetTwelveHourForecastInteractor
import interactor.settings.GetUnitsInteractor
import interactor.forecast.GetWeeklyForecastInteractor
import mapper.ForecastMapper
import model.forecast.CurrentConditions
import model.forecast.Forecast
import model.forecast.HourlyForecast
import repository.ForecastRepository

class ForecastRepositoryImpl(
	private val getDailyForecastInteractor: GetDailyForecastInteractor,
	private val getCurrentConditionsInteractor: GetCurrentConditionsInteractor,
	private val getWeeklyForecastInteractor: GetWeeklyForecastInteractor,
	private val getTwelveHourForecastInteractor: GetTwelveHourForecastInteractor,
	private val getUnitsInteractor: GetUnitsInteractor,
	private val forecastMapper: ForecastMapper
) : ForecastRepository {

	override suspend fun getDailyForecast(locationKey: String): Forecast {
		val isMetric = getUnitsInteractor()

		return forecastMapper.toDailyForecastInfo(getDailyForecastInteractor(locationKey, isMetric))
	}

	override suspend fun getCurrentConditions(locationKey: String): CurrentConditions {
		val apiCurrentConditions = getCurrentConditionsInteractor(locationKey)[0]
		val isMetric = getUnitsInteractor()

		return if (isMetric) {
			forecastMapper.toCurrentConditionsMetric(apiCurrentConditions)
		} else {
			forecastMapper.toCurrentConditionsImperial(apiCurrentConditions)
		}
	}

	override suspend fun getWeeklyForecast(locationKey: String): Forecast {
		val isMetric = getUnitsInteractor()

		return forecastMapper.toDailyForecastInfo(getWeeklyForecastInteractor(locationKey, isMetric))
	}

	override suspend fun getTwelveHourForecast(locationKey: String): HourlyForecast {
		val isMetric = getUnitsInteractor()

		return forecastMapper.toHourlyForecast(getTwelveHourForecastInteractor(locationKey, isMetric))
	}
}