package usecase.city.impl

import model.common.Resource
import repository.CityRepository
import usecase.city.QueryCitiesUseCase.QueryCitiesUseCaseResponse
import usecase.city.QueryCitiesUseCase
import usecase.city.QueryCitiesUseCase.QueryCitiesError

class QueryCitiesUseCaseImpl(private val cityRepository: CityRepository) : QueryCitiesUseCase {

	override suspend fun invoke(queryText: String): Resource<QueryCitiesUseCaseResponse> {
		return try {
			Resource.Success(QueryCitiesUseCaseResponse(cityRepository.queryCities(queryText)))
		} catch (throwable: Throwable) {
			return Resource.Error(QueryCitiesError.QUERY_CITIES_ERROR, throwable)
		}
	}
}