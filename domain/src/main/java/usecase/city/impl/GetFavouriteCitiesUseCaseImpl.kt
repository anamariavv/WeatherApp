package usecase.city.impl

import model.common.Resource
import repository.city.CityRepository
import usecase.city.GetFavouriteCitiesResponse
import usecase.city.GetFavouriteCitiesUseCase

class GetFavouriteCitiesUseCaseImpl(private val cityRepository: CityRepository) :
    GetFavouriteCitiesUseCase {

    override suspend fun invoke(): Resource<GetFavouriteCitiesResponse> {
        return Resource.Success(
            GetFavouriteCitiesResponse(
                cityRepository.getFavouriteCities()
            )
        )
    }
}