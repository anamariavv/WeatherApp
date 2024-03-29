package usecase.settings

import model.common.EmptyResource
import model.common.ErrorType

interface ToggleUnitsUseCase {

	suspend operator fun invoke(isMetric: Boolean): EmptyResource

	enum class ToggleUnitsUseCaseError : ErrorType {
		TOGGLE_UNITS_ERROR
	}
}