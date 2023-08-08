package repository.impl

import interactor.GetDailyForecastInteractor
import mapper.ForecastMapper
import model.forecast.Forecast
import repository.ForecastRepository

class ForecastRepositoryImpl(
	private val getDailyForecastInteractor: GetDailyForecastInteractor,
	private val forecastMapper: ForecastMapper
) : ForecastRepository {

	override suspend fun getDailyForecast(locationKey: String): Forecast {
		return forecastMapper.toDailyForecastInfo(getDailyForecastInteractor(locationKey, true))
	}
}