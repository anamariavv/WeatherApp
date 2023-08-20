package usecase.forecast.impl

import model.common.Resource
import repository.ForecastRepository
import usecase.forecast.GetWeeklyForecastUseCase
import usecase.forecast.GetWeeklyForecastUseCase.GetWeeklyForecastUseCaseResponse
import usecase.forecast.GetWeeklyForecastUseCase.GetWeeklyForecastError

class GetWeeklyForecastUseCaseImpl(private val forecastRepository: ForecastRepository) : GetWeeklyForecastUseCase {

	override suspend fun invoke(locationKey: String): Resource<GetWeeklyForecastUseCaseResponse> {
		return try {
			val data = forecastRepository.getWeeklyForecast(locationKey)
			Resource.Success(GetWeeklyForecastUseCaseResponse(data))
		} catch (throwable: Throwable) {
			Resource.Error(GetWeeklyForecastError.GET_WEEKLY_FORECAST_ERROR, throwable)
		}
	}
}