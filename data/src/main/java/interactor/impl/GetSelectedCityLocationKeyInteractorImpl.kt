package interactor.impl

import interactor.GetSelectedCityLocationKeyInteractor
import source.local.ApplicationStorage

class GetSelectedCityLocationKeyInteractorImpl(private val applicationStorage: ApplicationStorage) : GetSelectedCityLocationKeyInteractor {

	companion object {
		const val KEY = "LOCATION_KEY"
	}

	override suspend fun invoke(): String {
		return applicationStorage.getString(KEY)
	}
}