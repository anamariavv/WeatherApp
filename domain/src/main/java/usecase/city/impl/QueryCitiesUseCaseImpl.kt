package usecase.city.impl

import model.common.Resource
import repository.city.CityRepository
import usecase.city.QueryCitiesResponse
import usecase.city.QueryCitiesUseCase

class QueryCitiesUseCaseImpl(private val cityRepository: CityRepository) : QueryCitiesUseCase {

    override suspend fun invoke(queryText: String): Resource<QueryCitiesUseCase.QueryCitiesUseCaseResponse> {
        val list = try {
            cityRepository.queryCities(queryText)
        } catch (throwable: Throwable) {
            return Resource.Error(
                QueryCitiesUseCase.QueryCitiesError.GET_CITY_LIST_ERROR,
                throwable
            )
        }

        return Resource.Success(QueryCitiesResponse(list))
    }
}