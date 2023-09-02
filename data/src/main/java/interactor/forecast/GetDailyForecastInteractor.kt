package interactor.forecast

import model.network.forecast.daily.ApiForecast

interface GetDailyForecastInteractor {

	suspend operator fun invoke(locationKey: String, useMetricSystem: Boolean): ApiForecast
}