package interactor.city.impl

import interactor.city.SetSelectedCityLocationKeyInteractor
import source.local.ApplicationStorage

class SetSelectedCityLocationKeyInteractorImpl(private val applicationStorage: ApplicationStorage) : SetSelectedCityLocationKeyInteractor {

	companion object {
		const val KEY = "LOCATION_KEY"
	}

	override suspend fun invoke(locationKey: String) {
		applicationStorage.saveString(KEY, locationKey)
	}
}