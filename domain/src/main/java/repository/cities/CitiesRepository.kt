package repository.cities

import model.City

interface CitiesRepository {
    suspend fun queryCities(queryText: String): List<City>

    suspend fun getFavouriteCities(): List<City>
}