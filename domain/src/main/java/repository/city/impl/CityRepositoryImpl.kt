package repository.city.impl

import interactor.AddFavouriteCityInteractor
import interactor.GetFavouriteCitiesInteractor
import interactor.QueryCitiesInteractor
import interactor.RemoveFavouriteCityInteractor
import mapper.CityMapper
import model.City
import model.network.ApiCity
import repository.city.CityRepository

class CityRepositoryImpl(
    private val queryCitiesInteractor: QueryCitiesInteractor,
    private val getFavouriteCitiesInteractor: GetFavouriteCitiesInteractor,
    private val addFavouriteCityInteractor: AddFavouriteCityInteractor,
    private val removeFavouriteCityInteractor: RemoveFavouriteCityInteractor,
    private val cityMapper: CityMapper
) : CityRepository {

    override suspend fun queryCities(queryText: String): List<City> {
        //todo find a more efficient way
        val favouriteCities = getFavouriteCities()

        return queryCitiesInteractor(queryText).map { filterAndMapToCity(it, favouriteCities) }
    }

    private fun filterAndMapToCity(queryCity: ApiCity, favouriteCities: List<City>): City {
        var isFavourite = false

        favouriteCities.forEach { favCity ->
            if (queryCity.key == favCity.locationKey) {
                isFavourite = true
            }
        }

        return cityMapper.toCity(queryCity, isFavourite)
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