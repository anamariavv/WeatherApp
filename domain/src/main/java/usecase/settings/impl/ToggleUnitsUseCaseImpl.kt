package usecase.settings.impl

import model.common.EmptyResource
import model.common.Resource
import repository.SettingsRepository
import usecase.settings.ToggleUnitsUseCase
import usecase.settings.ToggleUnitsUseCase.ToggleUnitsUseCaseError


class ToggleUnitsUseCaseImpl(private val settingsRepository: SettingsRepository) : ToggleUnitsUseCase {

	override suspend fun invoke(isMetric: Boolean): EmptyResource {
		return try {
			settingsRepository.toggleUnits(isMetric)
			Resource.Success.empty()
		} catch (throwable: Throwable) {
			return Resource.Error(ToggleUnitsUseCaseError.TOGGLE_UNITS_ERROR, throwable)
		}
	}
}