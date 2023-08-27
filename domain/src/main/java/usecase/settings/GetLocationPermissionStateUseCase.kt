package usecase.settings

import model.common.ErrorType
import model.common.Resource

interface GetLocationPermissionStateUseCase {

	suspend operator fun invoke(): Resource<Boolean>

	enum class GetLocationPermissionStateError: ErrorType {
		GET_LOCATION_PERMISSION_ERROR
	}
}