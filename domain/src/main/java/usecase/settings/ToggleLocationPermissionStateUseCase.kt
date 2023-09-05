package usecase.settings

import model.common.EmptyResource
import model.common.ErrorType

interface ToggleLocationPermissionStateUseCase {

	suspend operator fun invoke(isGranted: Boolean): EmptyResource

	enum class ToggleLocationPermissionStateError : ErrorType {
		TOGGLE_PERMISSION_ERROR
	}
}