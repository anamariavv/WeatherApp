package usecase.forecast

import model.common.ErrorType
import model.common.Resource
import model.forecast.Forecast

interface GetWeeklyForecastUseCase {

	suspend operator fun invoke(locationKey: String): Resource<GetWeeklyForecastUseCaseResponse>

	class GetWeeklyForecastUseCaseResponse(forecast: Forecast)

	enum class GetWeeklyForecastError : ErrorType {
		GET_WEEKLY_FORECAST_ERROR
	}
}