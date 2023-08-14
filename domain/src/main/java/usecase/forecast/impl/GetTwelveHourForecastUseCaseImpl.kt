package usecase.forecast.impl

import model.common.Resource
import repository.ForecastRepository
import usecase.forecast.GetTwelveHourForecastUseCase
import usecase.forecast.GetTwelveHourForecastUseCase.GetTwelveHourForecastUseCaseResponse
import usecase.forecast.GetTwelveHourForecastUseCase.GetTwelveHourForecastError

class GetTwelveHourForecastUseCaseImpl(private val forecastRepository: ForecastRepository) : GetTwelveHourForecastUseCase {

	override suspend fun invoke(locationKey: String): Resource<GetTwelveHourForecastUseCaseResponse> {
		return try {
			val data = forecastRepository.getTwelveHourForecast(locationKey)
			Resource.Success(GetTwelveHourForecastUseCaseResponse(data))
		} catch (throwable: Throwable) {
			Resource.Error(GetTwelveHourForecastError.GET_TWELVE_HOUR_FORECAST_ERROR, throwable)
		}
	}
}