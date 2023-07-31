package usecase.city.impl

import model.City
import model.common.EmptyResource
import model.common.Resource
import repository.city.CityRepository
import usecase.city.ToggleFavouriteCityUseCase

class ToggleFavouriteCityUseCaseImpl(private val cityRepository: CityRepository) :
    ToggleFavouriteCityUseCase {

    override suspend fun invoke(city: City): EmptyResource {
        if (city.isFavourite) {
            cityRepository.removeFavouriteCity(city)
        } else {
            cityRepository.addFavouriteCity(city)
        }

        return Resource.Success.empty()
    }
}