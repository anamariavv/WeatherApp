package repository.impl

import android.util.Log
import interactor.GetCurrentConditionsInteractor
import interactor.GetDailyForecastInteractor
import interactor.GetTwelveHourForecastInteractor
import interactor.GetWeeklyForecastInteractor
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
	private val forecastMapper: ForecastMapper
) : ForecastRepository {

	override suspend fun getDailyForecast(locationKey: String): Forecast {
		return forecastMapper.toDailyForecastInfo(getDailyForecastInteractor(locationKey, true))
	}

	override suspend fun getCurrentConditions(locationKey: String): CurrentConditions {
		val apiCurrentConditions = getCurrentConditionsInteractor(locationKey)[0]
		return forecastMapper.toCurrentConditionsMetric(apiCurrentConditions)

		//todo: check for system units
		/*return if(useMetricSystem) {
			forecastMapper.toCurrentConditionsMetric(apiCurrentConditions)
		} else {
			forecastMapper.toCurrentConditionsImperial(apiCurrentConditions)
		}*/
	}

	override suspend fun getWeeklyForecast(locationKey: String): Forecast {
		return forecastMapper.toDailyForecastInfo(getWeeklyForecastInteractor(locationKey, true))
	}

	override suspend fun getTwelveHourForecast(locationKey: String): HourlyForecast {
		return forecastMapper.toHourlyForecast(getTwelveHourForecastInteractor(locationKey, true))
	}
}