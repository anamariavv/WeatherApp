package interactor.impl

import interactor.GetWeeklyForecastInteractor
import model.network.forecast.daily.ApiForecast
import source.network.ForecastService

class GetWeeklyForecastInteractorImpl(
	private val apiKey: String,
	private val forecastService: ForecastService
) : GetWeeklyForecastInteractor {

	companion object {
		const val SHOULD_GET_DETAILS = false
	}

	override suspend fun invoke(locationKey: String, useMetricSystem: Boolean): ApiForecast {
		return forecastService.getWeeklyForecast(locationKey = locationKey, apiKey = apiKey, details = SHOULD_GET_DETAILS, useMetricSystem)
	}
}
