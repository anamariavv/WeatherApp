package usecase.city.impl

import model.City
import model.common.EmptyResource
import model.common.Resource
import repository.city.CityRepository
import usecase.city.RemoveFavouriteCityUseCase
import usecase.city.RemoveFavouriteCityUseCase.RemoveFavouriteCityError

class RemoveFavouriteCityUseCaseImpl(private val cityRepository: CityRepository) :
	RemoveFavouriteCityUseCase {

	override suspend fun invoke(city: City): EmptyResource {
		return try {
			cityRepository.removeFavouriteCity(city)
			Resource.Success.empty()
		} catch (throwable: Throwable) {
			Resource.Error(RemoveFavouriteCityError.ERROR_REMOVING_CITY_FROM_FAVOURITES, throwable)
		}
	}
}