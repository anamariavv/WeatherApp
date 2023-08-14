package usecase.forecast

import model.common.ErrorType
import model.common.Resource
import model.forecast.HourlyForecast

interface GetTwelveHourForecastUseCase {

	suspend operator fun invoke(locationKey: String): Resource<GetTwelveHourForecastUseCaseResponse>

	class GetTwelveHourForecastUseCaseResponse(val forecast: HourlyForecast)

	enum class GetTwelveHourForecastError: ErrorType {
		GET_TWELVE_HOUR_FORECAST_ERROR
	}
}