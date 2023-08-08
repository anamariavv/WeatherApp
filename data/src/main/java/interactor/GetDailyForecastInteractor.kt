package interactor

import model.network.forecast.ApiForecast

interface GetDailyForecastInteractor {

	suspend operator fun invoke(locationKey: String, useMetricSystem: Boolean): ApiForecast
}