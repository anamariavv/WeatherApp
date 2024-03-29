package usecase.city

import model.city.City
import model.common.ErrorType
import model.common.Resource

interface QueryCitiesUseCase {
    suspend operator fun invoke(queryText: String): Resource<QueryCitiesUseCaseResponse>

    class QueryCitiesUseCaseResponse(val autocompleteCities: List<City>)

    enum class QueryCitiesError : ErrorType {
        QUERY_CITIES_ERROR
    }
}