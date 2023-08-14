package usecase.forecast

import model.common.ErrorType
import model.common.Resource
import model.forecast.CurrentConditions

interface GetCurrentConditionsUseCase {

	suspend operator fun invoke(locationKey: String): Resource<GetCurrentConditionsUseCaseResponse>

	class GetCurrentConditionsUseCaseResponse(val currentConditions: CurrentConditions)

	enum class GetCurrentConditionsError : ErrorType {
		GET_CONDITIONS_ERROR
	}
}