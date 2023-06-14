package usecase.cities

import repository.cities.CitiesRepository

class QueryCitiesUseCaseImpl(private val citiesRepository: CitiesRepository) : QueryCitiesUseCase {

    override suspend fun invoke(queryText: String): String {
        return citiesRepository.queryCities(queryText)
    }
}