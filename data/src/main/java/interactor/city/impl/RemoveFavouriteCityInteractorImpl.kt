package interactor.city.impl

import interactor.city.RemoveFavouriteCityInteractor
import model.local.city.FavouriteCity
import source.local.FavouriteCityDao

class RemoveFavouriteCityInteractorImpl(private val favouriteCityDao: FavouriteCityDao) :
	RemoveFavouriteCityInteractor {

    override suspend fun invoke(city: FavouriteCity) {
        favouriteCityDao.removeFavouriteCity(city)
    }
}