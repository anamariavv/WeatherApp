package interactor.settings.impl

import interactor.settings.ToggleUnitsInteractor
import source.local.ApplicationStorage

class ToggleUnitsInteractorImpl(private val applicationStorage: ApplicationStorage): ToggleUnitsInteractor {

	companion object {
		const val metricKey = "IS_METRIC"
	}

	override suspend fun invoke(isMetric: Boolean) {
		applicationStorage.saveBoolean(metricKey, isMetric)
	}
}