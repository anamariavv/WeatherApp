package usecase.settings.impl

import model.common.Resource
import repository.SettingsRepository
import usecase.settings.GetLocationPermissionStateUseCase
import usecase.settings.GetLocationPermissionStateUseCase.GetLocationPermissionStateError

class GetLocationPermissionStateUseCaseImpl(private val settingsRepository: SettingsRepository) : GetLocationPermissionStateUseCase {

	override suspend fun invoke(): Resource<Boolean> {
		return try {
			val data = settingsRepository.getLocationPermissionState()
			Resource.Success(data)
		} catch (throwable: Throwable) {
			Resource.Error(GetLocationPermissionStateError.GET_LOCATION_PERMISSION_ERROR, throwable)
		}
	}
}