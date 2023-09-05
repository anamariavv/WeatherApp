package usecase.settings.impl

import model.common.EmptyResource
import model.common.Resource
import repository.SettingsRepository
import usecase.settings.ToggleLocationPermissionStateUseCase
import usecase.settings.ToggleLocationPermissionStateUseCase.ToggleLocationPermissionStateError

class ToggleLocationPermissionStateUseCaseImpl(private val settingsRepository: SettingsRepository) : ToggleLocationPermissionStateUseCase {

	override suspend fun invoke(isGranted: Boolean): EmptyResource {
		return try {
			Resource.Success.empty()
		} catch (throwable: Throwable) {
			Resource.Error(ToggleLocationPermissionStateError.TOGGLE_PERMISSION_ERROR, throwable)
		}
	}
}