package interactor.forecast

import model.network.forecast.daily.ApiForecast

interface GetWeeklyForecastInteractor {

	suspend operator fun invoke(locationKey: String, useMetricSystem: Boolean): ApiForecast
}