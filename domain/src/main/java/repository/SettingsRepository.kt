package repository

interface SettingsRepository {

	suspend fun toggleUnits(isMetric: Boolean)

	suspend fun getUnits(): Boolean
}