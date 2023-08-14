package interactor.impl

import interactor.GetTwelveHourForecastInteractor
import model.network.forecast.hourly.ApiHourlyForecast
import source.network.ForecastService

class GetTwelveHourForecastInteractorImpl(
	private val apiKey: String,
	private val forecastService: ForecastService
) : GetTwelveHourForecastInteractor {

	companion object {
		const val SHOULD_GET_DETAILS = false
	}

	override suspend fun invoke(locationKey: String, useMetricSystem: Boolean): ApiHourlyForecast {
		return forecastService.getTwelveHourForecast(locationKey, apiKey, SHOULD_GET_DETAILS, useMetricSystem)
	}
}