package usecase.city.impl

import model.common.Resource
import repository.CityRepository
import usecase.city.GetSelectedCityLocationKeyUseCase
import usecase.city.GetSelectedCityLocationKeyUseCase.GetSelectedCityLocationKeyUseCaseResponse
import usecase.city.GetSelectedCityLocationKeyUseCase.GetSelectedCityLocationKeyError

class GetSelectedCityLocationKeyUseCaseImpl(private val cityRepository: CityRepository) : GetSelectedCityLocationKeyUseCase {

	override suspend fun invoke(): Resource<GetSelectedCityLocationKeyUseCaseResponse> {
		return try {
			val data = cityRepository.getSelectedCityLocationKey()
			Resource.Success(GetSelectedCityLocationKeyUseCaseResponse(data))
		} catch (throwable: Throwable) {
			Resource.Error(GetSelectedCityLocationKeyError.GET_LOCATION_KEY_ERROR, throwable)
		}
	}
}