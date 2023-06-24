package interactor

import model.local.FavouriteCity
import source.local.FavouriteCityDao

class AddFavouriteCityInteractorImpl(private val favouriteCityDao: FavouriteCityDao) :
    AddFavouriteCityInteractor {

    override suspend fun invoke(city: FavouriteCity) {
        favouriteCityDao.addFavouriteCity(city)
    }
}