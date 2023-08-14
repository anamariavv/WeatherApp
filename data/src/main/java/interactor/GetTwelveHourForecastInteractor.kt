package interactor

import model.network.forecast.hourly.ApiHourlyForecast

interface GetTwelveHourForecastInteractor {

	suspend operator fun invoke(locationKey: String, useMetricSystem: Boolean): ApiHourlyForecast
}