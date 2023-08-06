package usecase.city

import model.City
import model.common.ErrorType
import model.common.Resource

interface GetFavouriteCitiesUseCase {

	suspend operator fun invoke(): Resource<GetFavouriteCitiesUseCaseResponse>

	data class GetFavouriteCitiesUseCaseResponse(val list: List<City>)

	enum class GetFavouriteCitiesError : ErrorType {
		GET_FAVOURITES_ERROR
	}
}