package usecase.cities

import model.City

interface GetFavouriteCitiesUseCase {

    suspend operator fun invoke() : List<City>
}