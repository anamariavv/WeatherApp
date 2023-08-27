package interactor.impl

import interactor.GetLocationPermissionStateInteractor
import source.local.ApplicationStorage

class GetLocationPermissionStateInteractorImpl(private val applicationStorage: ApplicationStorage) : GetLocationPermissionStateInteractor {

	companion object {
		const val key = "LOCATION_PERMISSION_GRANTED"
	}

	override suspend fun invoke(): Boolean {
		return applicationStorage.getBoolean(key, false)
	}
}