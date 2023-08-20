package interactor.impl

import interactor.GetCityBasedOnCoordinatesInteractor
import model.network.city.ApiCity
import source.network.LocationsService

class GetCityBasedOnCoordinatesInteractorImpl(
	private val apiKey: String, private val locationsService: LocationsService
) : GetCityBasedOnCoordinatesInteractor {

	override suspend fun invoke(coordinates: String): ApiCity {
		return locationsService.getLocationFromCoordinates(apiKey, coordinates)
	}
}