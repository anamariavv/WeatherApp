package usecase.city

import model.City

interface GetFavouriteCitiesUseCase {

    suspend operator fun invoke() : List<City>
}