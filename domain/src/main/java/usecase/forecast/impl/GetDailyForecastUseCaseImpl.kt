package usecase.forecast.impl

import model.common.Resource
import model.forecast.Forecast
import repository.ForecastRepository
import usecase.forecast.GetDailyForecastUseCase
import usecase.forecast.GetDailyForecastUseCase.GetDailyForecastError
import usecase.forecast.GetDailyForecastUseCase.GetDailyForecastUseCaeResponse

class GetDailyForecastUseCaseImpl(private val forecastRepository: ForecastRepository) : GetDailyForecastUseCase {

	override suspend fun invoke(locationKey: String): Resource<GetDailyForecastUseCaeResponse> {
		return try {
			val forecast = forecastRepository.getDailyForecast(locationKey)

			Resource.Success(GetDailyForecastUseCaeResponse(forecast))
		} catch (throwable: Throwable) {
			Resource.Error(GetDailyForecastError.GET_FORECAST_ERROR, throwable)
		}
	}
}