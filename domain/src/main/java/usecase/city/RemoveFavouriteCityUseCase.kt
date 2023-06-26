package usecase.city

import model.City

interface RemoveFavouriteCityUseCase {

    suspend operator fun invoke(city: City)
}