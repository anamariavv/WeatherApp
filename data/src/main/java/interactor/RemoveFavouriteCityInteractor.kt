package interactor

import model.local.FavouriteCity

interface RemoveFavouriteCityInteractor {

    suspend operator fun invoke(city: FavouriteCity)
}