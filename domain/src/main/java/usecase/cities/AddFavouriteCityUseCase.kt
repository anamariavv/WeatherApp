package usecase.cities

import model.City

interface AddFavouriteCityUseCase {

    suspend operator fun invoke(city: City)
}