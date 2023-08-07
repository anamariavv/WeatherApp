package usecase.city.impl

import model.City
import model.common.EmptyResource
import model.common.Resource
import repository.city.CityRepository
import usecase.city.ToggleFavouriteCityUseCase
import usecase.city.ToggleFavouriteCityUseCase.ToggleFavouriteCitiesError

class ToggleFavouriteCityUseCaseImpl(
	private val cityRepository: CityRepository
) : ToggleFavouriteCityUseCase {

	override suspend fun invoke(city: City): EmptyResource {

		return try {
			if (city.isFavourite) {
				cityRepository.removeFavouriteCity(city)
			} else {
				cityRepository.addFavouriteCity(city)
			}
			Resource.Success.empty()
		} catch (throwable: Throwable) {
			val errorType = if (city.isFavourite) {
				ToggleFavouriteCitiesError.REMOVE_FAVOURITE_CITY_ERROR
			} else {
				ToggleFavouriteCitiesError.ADD_FAVOURITE_CITY_ERROR
			}
			return Resource.Error(errorType, throwable)
		}
	}
}