package repository.cities

interface CitiesRepository {
    suspend fun queryCities(queryText: String): String
}