package usecase.city.impl

import model.City
import repository.city.CityRepository
import usecase.city.RemoveFavouriteCityUseCase

class RemoveFavouriteCityUseCaseImpl(private val cityRepository: CityRepository) :
    RemoveFavouriteCityUseCase {

    override suspend fun invoke(city: City) {
        cityRepository.removeFavouriteCity(city)
    }
}