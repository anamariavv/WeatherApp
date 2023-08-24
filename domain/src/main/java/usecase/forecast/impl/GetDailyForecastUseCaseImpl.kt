package usecase.forecast.impl

import model.common.Resource
import repository.ForecastRepository
import usecase.forecast.GetDailyForecastUseCase
import usecase.forecast.GetDailyForecastUseCase.GetDailyForecastError
import usecase.forecast.GetDailyForecastUseCase.GetDailyForecastUseCaseResponse
import javax.inject.Inject

class GetDailyForecastUseCaseImpl (private val forecastRepository: ForecastRepository) : GetDailyForecastUseCase {

	override suspend fun invoke(locationKey: String): Resource<GetDailyForecastUseCaseResponse> {
		return try {
			val forecast = forecastRepository.getDailyForecast(locationKey)

			Resource.Success(GetDailyForecastUseCaseResponse(forecast))
		} catch (throwable: Throwable) {
			Resource.Error(GetDailyForecastError.GET_FORECAST_ERROR, throwable)
		}
	}
}