package interactor

import model.local.FavouriteCity

interface AddFavouriteCityInteractor {

    suspend operator fun invoke(city: FavouriteCity)
}