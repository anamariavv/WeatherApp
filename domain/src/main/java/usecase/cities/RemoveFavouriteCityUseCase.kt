package usecase.cities

import model.City

interface RemoveFavouriteCityUseCase {

    suspend operator fun invoke(city: City)
}