package usecase.city

import model.City

interface ToggleFavouriteCityUseCase {

    suspend operator fun invoke(city: City)
}