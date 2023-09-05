package usecase.settings

import model.common.ErrorType
import model.common.Resource

interface GetUnitsUseCase {

	suspend operator fun invoke(): Resource<Boolean>

	enum class GetUnitsUseCaseError : ErrorType {
		GET_UNITS_ERROR
	}
}