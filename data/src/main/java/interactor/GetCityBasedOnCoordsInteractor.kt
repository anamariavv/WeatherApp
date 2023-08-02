package interactor

import model.network.ApiCity

interface GetCityBasedOnCoordsInteractor {

	suspend operator fun invoke(coordinates: String): ApiCity
}