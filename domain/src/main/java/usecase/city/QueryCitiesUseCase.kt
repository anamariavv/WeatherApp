package usecase.city

import model.City
import model.common.ErrorType
import model.common.Resource

typealias QueryCitiesResponse = QueryCitiesUseCase.QueryCitiesUseCaseResponse

interface QueryCitiesUseCase {
    suspend operator fun invoke(queryText: String): Resource<QueryCitiesResponse>

    class QueryCitiesUseCaseResponse(val autocompleteCities: List<City>)

    enum class QueryCitiesError : ErrorType {
        GET_CITY_LIST_ERROR
    }
}