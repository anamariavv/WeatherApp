package interactor

import model.network.forecast.ApiDailyForecastInfo

interface GetDailyForecastInteractor {

	suspend operator fun invoke(locationKey: String, useMetricSystem: Boolean): ApiDailyForecastInfo
}