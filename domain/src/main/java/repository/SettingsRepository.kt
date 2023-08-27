package repository

interface SettingsRepository {

	suspend fun toggleUnits(isMetric: Boolean)

	suspend fun getUnits(): Boolean

	suspend fun getLocationPermissionState(): Boolean

	suspend fun toggleLocationPermissionState(isGranted: Boolean)
}