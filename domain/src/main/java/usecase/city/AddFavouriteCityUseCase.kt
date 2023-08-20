package usecase.city

import model.city.City

interface AddFavouriteCityUseCase {

    suspend operator fun invoke(city: City)
}