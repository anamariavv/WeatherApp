package interactor

import model.local.FavouriteCityWithCountry

interface GetFavouriteCitiesInteractor {
    suspend operator fun invoke(): List<FavouriteCityWithCountry>
}