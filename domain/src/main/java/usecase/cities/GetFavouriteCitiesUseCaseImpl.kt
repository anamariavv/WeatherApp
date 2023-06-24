package usecase.cities

import model.City
import repository.cities.CitiesRepository

class GetFavouriteCitiesUseCaseImpl(private val citiesRepository: CitiesRepository) :
    GetFavouriteCitiesUseCase {

    override suspend fun invoke(): List<City> {
        return citiesRepository.getFavouriteCities()
    }
}