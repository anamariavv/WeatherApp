package interactor.city

import model.local.city.FavouriteCity

interface RemoveFavouriteCityInteractor {

    suspend operator fun invoke(city: FavouriteCity)
}