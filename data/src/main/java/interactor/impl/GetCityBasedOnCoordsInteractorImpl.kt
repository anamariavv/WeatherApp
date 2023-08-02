package interactor.impl

import interactor.GetCityBasedOnCoordsInteractor
import model.network.ApiCity
import source.network.LocationsService

class GetCityBasedOnCoordsInteractorImpl(private val locationsService: LocationsService): GetCityBasedOnCoordsInteractor {

	override suspend fun invoke(coordinates: String): ApiCity {
		return locationsService.getLocationFromCoordinates("", coordinates)
	}
}