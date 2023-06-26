package usecase.city.impl

import model.City
import repository.city.CityRepository
import usecase.city.GetFavouriteCitiesUseCase

class GetFavouriteCitiesUseCaseImpl(private val cityRepository: CityRepository) :
    GetFavouriteCitiesUseCase {

    override suspend fun invoke(): List<City> {
        return cityRepository.getFavouriteCities()
    }
}