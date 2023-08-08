package usecase.city.impl

import model.city.City
import repository.CityRepository
import usecase.city.AddFavouriteCityUseCase

class AddFavouriteCityUseCaseImpl(private val cityRepository: CityRepository) :
    AddFavouriteCityUseCase {

    override suspend fun invoke(city: City) {
        cityRepository.addFavouriteCity(city)
    }
}