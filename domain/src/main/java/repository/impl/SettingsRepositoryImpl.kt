package repository.impl

import interactor.GetLocationPermissionStateInteractor
import interactor.GetUnitsInteractor
import interactor.ToggleLocationPermissionStateInteractor
import interactor.ToggleUnitsInteractor
import repository.SettingsRepository

class SettingsRepositoryImpl(
	private val toggleUnitsInteractor: ToggleUnitsInteractor,
	private val getUnitsInteractor: GetUnitsInteractor,
	private val toggleLocationPermissionStateInteractor: ToggleLocationPermissionStateInteractor,
	private val getLocationPermissionStateInteractor: GetLocationPermissionStateInteractor
) : SettingsRepository {

	override suspend fun toggleUnits(isMetric: Boolean) {
		toggleUnitsInteractor(isMetric)
	}

	override suspend fun getUnits(): Boolean {
		return getUnitsInteractor()
	}

	override suspend fun getLocationPermissionState(): Boolean {
		return getLocationPermissionStateInteractor()
	}

	override suspend fun toggleLocationPermissionState(isGranted: Boolean) {
		toggleLocationPermissionStateInteractor(isGranted)
	}
}