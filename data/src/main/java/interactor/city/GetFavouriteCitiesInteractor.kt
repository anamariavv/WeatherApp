package interactor.city

import model.local.city.FavouriteCity

interface GetFavouriteCitiesInteractor {
    suspend operator fun invoke(): List<FavouriteCity>
}