package repository.impl

import interactor.AddFavouriteCityInteractor
import interactor.GetCityBasedOnCoordinatesInteractor
import interactor.GetCurrentGeoLocationInteractor
import interactor.GetFavouriteCitiesInteractor
import interactor.QueryCitiesInteractor
import interactor.RemoveFavouriteCityInteractor
import mapper.CityMapper
import model.city.City
import model.network.city.ApiCity
import repository.CityRepository

class CityRepositoryImpl(
	private val queryCitiesInteractor: QueryCitiesInteractor,
	private val getFavouriteCitiesInteractor: GetFavouriteCitiesInteractor,
	private val addFavouriteCityInteractor: AddFavouriteCityInteractor,
	private val removeFavouriteCityInteractor: RemoveFavouriteCityInteractor,
	private val getCurrentGeoLocationInteractor: GetCurrentGeoLocationInteractor,
	private val getCityBasedOnCoordinatesInteractor: GetCityBasedOnCoordinatesInteractor,
	private val cityMapper: CityMapper
) : CityRepository {

	override suspend fun queryCities(queryText: String): List<City> {
		//todo find a more efficient way
		val favouriteCities = getFavouriteCities()

		return queryCitiesInteractor(queryText).map { filterAndMapToCity(it, favouriteCities) }
	}

	private fun filterAndMapToCity(city: ApiCity, favouriteCities: List<City>): City {
		var isFavourite = false

		favouriteCities.forEach { favCity ->
			if (city.key == favCity.locationKey) {
				isFavourite = true
			}
		}

		return cityMapper.toCity(city, isFavourite)
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

	override suspend fun getCurrentCity(): City {
		val coordinates = getCurrentCoordinates()
		val city = getCityBasedOnCoordinatesInteractor(String.format("%f,%f", coordinates.first, coordinates.second))

		return filterAndMapToCity(city, getFavouriteCities())
	}

	private suspend fun getCurrentCoordinates(): Pair<Double, Double> {
		val geoLocation = getCurrentGeoLocationInteractor()

		return Pair(geoLocation.latitude, geoLocation.longitude)
	}
}