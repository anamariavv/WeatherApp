package usecase.forecast.impl

import model.common.Resource
import repository.ForecastRepository
import usecase.forecast.GetCurrentConditionsUseCase
import usecase.forecast.GetCurrentConditionsUseCase.GetCurrentConditionsUseCaseResponse
import usecase.forecast.GetCurrentConditionsUseCase.GetCurrentConditionsError

class GetCurrentConditionsUseCaseImpl(private val forecastRepository: ForecastRepository) : GetCurrentConditionsUseCase {

	override suspend fun invoke(locationKey: String): Resource<GetCurrentConditionsUseCaseResponse> {
		return try {
			val data = forecastRepository.getCurrentConditions(locationKey)
			Resource.Success(GetCurrentConditionsUseCaseResponse(data))
		} catch (throwable: Throwable) {
			Resource.Error(GetCurrentConditionsError.GET_CONDITIONS_ERROR, throwable)
		}
	}
}