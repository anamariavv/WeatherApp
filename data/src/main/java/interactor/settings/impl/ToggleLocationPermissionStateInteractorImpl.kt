package interactor.settings.impl

import interactor.settings.ToggleLocationPermissionStateInteractor
import source.local.ApplicationStorage

class ToggleLocationPermissionStateInteractorImpl(private val applicationStorage: ApplicationStorage) : ToggleLocationPermissionStateInteractor {

	companion object {
		const val key = "LOCATION_PERMISSION_GRANTED"
	}

	override suspend fun invoke(isGranted: Boolean) {
		applicationStorage.saveBoolean(key, isGranted)
	}
}