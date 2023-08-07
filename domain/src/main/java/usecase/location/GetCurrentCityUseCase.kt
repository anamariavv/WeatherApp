package usecase.location

import model.City
import model.common.ErrorType
import model.common.Resource

interface GetCurrentCityUseCase {

	suspend operator fun invoke(): Resource<GetCurrentCityUseCaseResponse>

	class GetCurrentCityUseCaseResponse(val city: City)

	enum class GetCurrentCityUseCaseError : ErrorType {
		GET_LOCATION_ERROR
	}
}