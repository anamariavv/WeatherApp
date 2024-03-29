package interactor.city

import model.network.city.ApiCity

interface GetCityBasedOnCoordinatesInteractor {

	suspend operator fun invoke(coordinates: String): ApiCity
}