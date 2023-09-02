package interactor.city.impl

import interactor.city.GetFavouriteCitiesInteractor
import model.local.city.FavouriteCity
import source.local.FavouriteCityDao

class GetFavouriteCitiesInteractorImpl(private val favouriteCityDao: FavouriteCityDao) :
	GetFavouriteCitiesInteractor {

    override suspend fun invoke(): List<FavouriteCity> {
        return favouriteCityDao.getAll()
    }
}