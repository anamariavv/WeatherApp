package interactor.impl

import interactor.GetCurrentConditionsInteractor
import model.network.forecast.current.ApiCurrentConditions
import source.network.ForecastService

class GetCurrentConditionsInteractorImpl(
	private val apiKey: String,
	private val forecastService: ForecastService
) : GetCurrentConditionsInteractor {

	companion object {
		const val SHOULD_GET_DETAILS = true
	}

	override suspend fun invoke(locationKey: String): ApiCurrentConditions {
		return forecastService.getCurrentConditions(apiKey = apiKey, locationKey = locationKey, details = SHOULD_GET_DETAILS)
	}
}