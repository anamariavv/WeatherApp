package usecase.city

import model.common.ErrorType
import model.common.Resource

interface GetSelectedCityLocationKeyUseCase {

	suspend operator fun invoke(): Resource<GetSelectedCityLocationKeyUseCaseResponse>

	class GetSelectedCityLocationKeyUseCaseResponse(val locationKey: String)

	enum class GetSelectedCityLocationKeyError: ErrorType {
		GET_LOCATION_KEY_ERROR
	}
}