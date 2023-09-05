package interactor.city.impl

import interactor.city.AddFavouriteCityInteractor
import model.local.city.FavouriteCity
import source.local.FavouriteCityDao

class AddFavouriteCityInteractorImpl(private val favouriteCityDao: FavouriteCityDao) :
    AddFavouriteCityInteractor {

    override suspend fun invoke(city: FavouriteCity) {
        favouriteCityDao.addFavouriteCity(city)
    }
}