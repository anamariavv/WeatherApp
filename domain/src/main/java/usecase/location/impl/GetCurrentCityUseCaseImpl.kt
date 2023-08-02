package usecase.location.impl

import model.common.Resource
import repository.city.CityRepository
import usecase.location.GetCurrentCityUseCase
import usecase.location.GetCurrentCityUseCase.GetCurrentCityUseCaseResponse
import usecase.location.GetCurrentCityUseCase.GetCurrentCityUseCaseError

class GetCurrentCityUseCaseImpl(private val cityRepository: CityRepository) : GetCurrentCityUseCase {

	override suspend fun invoke(): Resource<GetCurrentCityUseCaseResponse> {
		return try {
			val city = cityRepository.getCurrentCity()
			Resource.Success(GetCurrentCityUseCaseResponse(city))
		} catch (throwable: Throwable) {
			Resource.Error(GetCurrentCityUseCaseError.ERROR_GETTING_LOCATION, throwable)
		}
	}
}