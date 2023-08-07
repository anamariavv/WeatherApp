package usecase.city.impl

import model.City
import repository.city.CityRepository
import usecase.city.AddFavouriteCityUseCase

class AddFavouriteCityUseCaseImpl(private val cityRepository: CityRepository) :
    AddFavouriteCityUseCase {

    override suspend fun invoke(city: City) {
        cityRepository.addFavouriteCity(city)
    }
}