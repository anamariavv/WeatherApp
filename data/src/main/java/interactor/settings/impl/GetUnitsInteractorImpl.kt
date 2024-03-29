package interactor.settings.impl

import interactor.settings.GetUnitsInteractor
import source.local.ApplicationStorage

class GetUnitsInteractorImpl(private val applicationStorage: ApplicationStorage) : GetUnitsInteractor {

	companion object {
		const val metricKey = "IS_METRIC"
	}

	override suspend fun invoke(): Boolean {
		return applicationStorage.getBoolean(metricKey)
	}
}