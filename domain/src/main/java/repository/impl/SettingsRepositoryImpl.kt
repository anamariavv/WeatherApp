package repository.impl

import interactor.GetUnitsInteractor
import interactor.ToggleUnitsInteractor
import repository.SettingsRepository

class SettingsRepositoryImpl(
	private val toggleUnitsInteractor: ToggleUnitsInteractor,
	private val getUnitsInteractor: GetUnitsInteractor
) : SettingsRepository {

	override suspend fun toggleUnits(isMetric: Boolean) {
		toggleUnitsInteractor(isMetric)
	}

	override suspend fun getUnits(): Boolean {
		return getUnitsInteractor()
	}
}