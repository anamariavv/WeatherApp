package repository.cities

import interactor.GetFavouriteCitiesInteractor
import interactor.QueryCitiesInteractor
import mapper.CityMapper
import model.City

class CitiesRepositoryImpl(
    private val queryCitiesInteractor: QueryCitiesInteractor,
    private val getFavouriteCitiesInteractor: GetFavouriteCitiesInteractor,
    private val cityMapper: CityMapper
) : CitiesRepository {

    override suspend fun queryCities(queryText: String): List<City> {
        return queryCitiesInteractor(queryText).map { cityMapper.toCity(it) }
    }

    override suspend fun getFavouriteCities(): List<City> {
        return getFavouriteCitiesInteractor().map { cityMapper.toCity(it) }
    }
}