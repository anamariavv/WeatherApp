package repository

import model.city.City

interface CityRepository {
    suspend fun queryCities(queryText: String): List<City>

    suspend fun getFavouriteCities(): List<City>

    suspend fun addFavouriteCity(city: City)

    suspend fun removeFavouriteCity(city: City)

    suspend fun getCurrentCity(): City

    suspend fun getSelectedCityLocationKey(): String

    suspend fun setSelectedCityLocationKey(locationKey: String)
}