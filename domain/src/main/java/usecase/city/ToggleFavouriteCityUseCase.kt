package usecase.city

import model.City
import model.common.EmptyResource
import model.common.ErrorType

interface ToggleFavouriteCityUseCase {

    suspend operator fun invoke(city: City): EmptyResource

    enum class ToggleFavouriteCitiesError: ErrorType {
        ADD_FAVOURITE_CITY_ERROR,
        REMOVE_FAVOURITE_CITY_ERROR
    }
}