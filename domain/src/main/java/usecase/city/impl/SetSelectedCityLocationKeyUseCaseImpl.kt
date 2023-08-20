package usecase.city.impl


import model.common.Resource
import repository.CityRepository
import usecase.city.SetSelectedCityLocationKeyUseCase
import usecase.city.SetSelectedCityLocationKeyUseCase.SetSelectedCityLocationKeyError

class SetSelectedCityLocationKeyUseCaseImpl(private val cityRepository: CityRepository) : SetSelectedCityLocationKeyUseCase {

	override suspend fun invoke(locationKey: String): Resource<Unit> {
		return try {
			cityRepository.setSelectedCityLocationKey(locationKey)
			Resource.Success.empty()
		} catch (throwable: Throwable) {
			Resource.Error(SetSelectedCityLocationKeyError.SET_LOCATION_KEY_ERROR, throwable)
		}
	}
}