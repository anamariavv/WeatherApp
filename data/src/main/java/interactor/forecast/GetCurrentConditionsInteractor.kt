package interactor.forecast

import model.network.forecast.current.ApiCurrentConditions

interface GetCurrentConditionsInteractor {

	suspend operator fun invoke(locationKey: String): ApiCurrentConditions
}