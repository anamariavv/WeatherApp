package interactor

import model.local.FavouriteCity

interface GetFavouriteCitiesInteractor {
    suspend operator fun invoke(): List<FavouriteCity>
}