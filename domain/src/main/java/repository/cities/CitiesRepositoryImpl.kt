package repository.cities

import interactor.AddFavouriteCityInteractor
import interactor.GetFavouriteCitiesInteractor
import interactor.QueryCitiesInteractor
import interactor.RemoveFavouriteCityInteractor
import mapper.CityMapper
import model.City

class CitiesRepositoryImpl(
    private val queryCitiesInteractor: QueryCitiesInteractor,
    private val getFavouriteCitiesInteractor: GetFavouriteCitiesInteractor,
    private val addFavouriteCityInteractor: AddFavouriteCityInteractor,
    private val removeFavouriteCityInteractor: RemoveFavouriteCityInteractor,
    private val cityMapper: CityMapper
) : CitiesRepository {

    override suspend fun queryCities(queryText: String): List<City> {
        return queryCitiesInteractor(queryText).map { cityMapper.toCity(it) }
    }

    override suspend fun getFavouriteCities(): List<City> {
        return getFavouriteCitiesInteractor().map { cityMapper.toCity(it) }
    }

    override suspend fun addFavouriteCity(city: City) {
        addFavouriteCityInteractor(cityMapper.toFavouriteCity(city))
    }

    override suspend fun removeFavouriteCity(city: City) {
        removeFavouriteCityInteractor(cityMapper.toFavouriteCity(city))
    }
}