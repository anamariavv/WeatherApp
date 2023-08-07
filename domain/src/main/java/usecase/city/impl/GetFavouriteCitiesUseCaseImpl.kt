package usecase.city.impl

import model.common.Resource
import repository.city.CityRepository
import usecase.city.GetFavouriteCitiesUseCase.GetFavouriteCitiesUseCaseResponse
import usecase.city.GetFavouriteCitiesUseCase.GetFavouriteCitiesError
import usecase.city.GetFavouriteCitiesUseCase

class GetFavouriteCitiesUseCaseImpl(private val cityRepository: CityRepository) :
	GetFavouriteCitiesUseCase {

	override suspend fun invoke(): Resource<GetFavouriteCitiesUseCaseResponse> {
		return try {
			Resource.Success(GetFavouriteCitiesUseCaseResponse(cityRepository.getFavouriteCities()))
		} catch (throwable: Throwable) {
			Resource.Error(GetFavouriteCitiesError.GET_FAVOURITES_ERROR, throwable)
		}
	}
}