package usecase.city

import model.City
import model.common.ErrorType
import model.common.Resource

typealias GetFavouriteCitiesResponse = GetFavouriteCitiesUseCase.GetFavouriteCitiesUseCaseResponse

interface GetFavouriteCitiesUseCase {

	suspend operator fun invoke(): Resource<GetFavouriteCitiesResponse>

	data class GetFavouriteCitiesUseCaseResponse(val list: List<City>)

	enum class GetFavouriteCitiesError : ErrorType {
		ERROR_GETTING_LIST
	}
}