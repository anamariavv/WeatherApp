package interactor.impl

import interactor.RemoveFavouriteCityInteractor
import model.local.FavouriteCity
import source.local.FavouriteCityDao

class RemoveFavouriteCityInteractorImpl(private val favouriteCityDao: FavouriteCityDao) :
    RemoveFavouriteCityInteractor {

    override suspend fun invoke(city: FavouriteCity) {
        favouriteCityDao.removeFavouriteCity(city)
    }
}