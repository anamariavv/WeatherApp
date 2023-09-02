package interactor.city

import model.local.city.FavouriteCity

interface AddFavouriteCityInteractor {

    suspend operator fun invoke(city: FavouriteCity)
}