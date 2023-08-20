package usecase.city

import model.common.ErrorType
import model.common.Resource

interface SetSelectedCityLocationKeyUseCase {
	suspend operator fun invoke(locationKey: String): Resource<Unit>

	enum class SetSelectedCityLocationKeyError : ErrorType {
		SET_LOCATION_KEY_ERROR
	}
}