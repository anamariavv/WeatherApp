package usecase.forecast

import model.common.ErrorType
import model.common.Resource
import model.forecast.Forecast

interface GetDailyForecastUseCase {

	suspend operator fun invoke(locationKey: String): Resource<GetDailyForecastUseCaseResponse>

	class GetDailyForecastUseCaseResponse(val forecast: Forecast)

	enum class GetDailyForecastError : ErrorType {
		GET_FORECAST_ERROR
	}

}