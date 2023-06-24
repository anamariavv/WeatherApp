package usecase.cities

import model.City
import repository.cities.CitiesRepository

class AddFavouriteCityUseCaseImpl(private val citiesRepository: CitiesRepository) : AddFavouriteCityUseCase {

    override suspend fun invoke(city: City) {
        citiesRepository.addFavouriteCity(city)
    }
}