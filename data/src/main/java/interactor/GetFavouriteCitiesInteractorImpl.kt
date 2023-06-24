package interactor

import model.local.FavouriteCity
import source.local.FavouriteCityDao

class GetFavouriteCitiesInteractorImpl(private val favouriteCityDao: FavouriteCityDao) : GetFavouriteCitiesInteractor {

    override suspend fun invoke(): List<FavouriteCity> {
        return favouriteCityDao.getAll()
    }
}