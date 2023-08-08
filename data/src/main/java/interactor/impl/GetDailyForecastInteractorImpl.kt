package interactor.impl

import interactor.GetDailyForecastInteractor
import model.network.forecast.ApiDailyForecastInfo
import source.network.ForecastService

class GetDailyForecastInteractorImpl(
	private val apiKey: String,
	private val forecastService: ForecastService
) : GetDailyForecastInteractor {

	companion object {
		const val GET_DETAILS = true
	}

	override suspend fun invoke(locationKey: String, useMetricSystem: Boolean): ApiDailyForecastInfo {
		return forecastService.getDailyForecast(locationKey, apiKey, GET_DETAILS, useMetricSystem)
	}
}