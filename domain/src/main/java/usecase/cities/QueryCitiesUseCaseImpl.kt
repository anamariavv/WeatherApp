package usecase.cities

import android.util.Log
import model.common.Resource
import repository.cities.CitiesRepository

class QueryCitiesUseCaseImpl(private val citiesRepository: CitiesRepository) : QueryCitiesUseCase {

    override suspend fun invoke(queryText: String): Resource<QueryCitiesUseCase.QueryCitiesUseCaseResponse> {
        val list =  try {
            citiesRepository.queryCities(queryText)
        } catch (throwable: Throwable) {
            return Resource.Error(QueryCitiesUseCase.QueryCitiesError.GET_CITY_LIST_ERROR, throwable)
        }

        return Resource.Success(QueryCitiesUseCase.QueryCitiesUseCaseResponse(list))
    }
}