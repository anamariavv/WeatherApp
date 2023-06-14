package repository.cities

import interactor.QueryCitiesInteractor

class CitiesRepositoryImpl(private val queryCitiesInteractor: QueryCitiesInteractor): CitiesRepository {

    override suspend fun queryCities(queryText: String): String {
       return queryCitiesInteractor(queryText)
    }
}