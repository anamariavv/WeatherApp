package usecase.cities

import model.City
import repository.cities.CitiesRepository

class RemoveFavouriteCityUseCaseImpl(private val citiesRepository: CitiesRepository) :
    RemoveFavouriteCityUseCase {

    override suspend fun invoke(city: City) {
        citiesRepository.removeFavouriteCity(city)
    }
}