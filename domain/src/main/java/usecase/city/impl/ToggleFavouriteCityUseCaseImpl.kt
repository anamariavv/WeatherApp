package usecase.city.impl

import model.City
import repository.city.CityRepository
import usecase.city.ToggleFavouriteCityUseCase

class ToggleFavouriteCityUseCaseImpl(private val cityRepository: CityRepository) :
    ToggleFavouriteCityUseCase {

    override suspend fun invoke(city: City) {
        if (city.isFavourite) {
            cityRepository.removeFavouriteCity(city)
        } else {
            cityRepository.addFavouriteCity(city)
        }
    }
}