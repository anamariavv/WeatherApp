package interactor.forecast.impl

import interactor.forecast.GetCurrentConditionsInteractor
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
		return forecastService.getCurrentConditions( locationKey, apiKey, SHOULD_GET_DETAILS)
	}
}