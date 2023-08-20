package usecase.city

import model.city.City
import model.common.EmptyResource
import model.common.ErrorType

interface RemoveFavouriteCityUseCase {

    suspend operator fun invoke(city: City): EmptyResource

    enum class RemoveFavouriteCityError : ErrorType {
        ERROR_REMOVING_CITY_FROM_FAVOURITES
    }
}