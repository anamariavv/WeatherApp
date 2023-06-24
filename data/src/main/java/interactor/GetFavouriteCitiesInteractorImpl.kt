package interactor

import model.local.FavouriteCityWithCountry
import source.local.FavouriteCityDao

class GetFavouriteCitiesInteractorImpl(private val favouriteCityDao: FavouriteCityDao) : GetFavouriteCitiesInteractor {

    override suspend fun invoke(): List<FavouriteCityWithCountry> {
        return favouriteCityDao.getAll()
    }
}