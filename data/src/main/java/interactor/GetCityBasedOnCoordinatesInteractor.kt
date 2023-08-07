package interactor

import model.network.ApiCity

interface GetCityBasedOnCoordinatesInteractor {

	suspend operator fun invoke(coordinates: String): ApiCity
}