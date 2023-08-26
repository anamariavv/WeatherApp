package usecase.settings.impl

import model.common.Resource
import repository.SettingsRepository
import usecase.settings.GetUnitsUseCase
import usecase.settings.GetUnitsUseCase.GetMetricUnitsUseCaseError

class GetUnitsUseCaseImpl(private val settingsRepository: SettingsRepository) : GetUnitsUseCase {

	override suspend fun invoke(): Resource<Boolean> {
		return try {
			val data = settingsRepository.getUnits()
			Resource.Success(data)
		} catch (throwable: Throwable) {
			return Resource.Error(GetMetricUnitsUseCaseError.GET_UNITS_ERROR, throwable)
		}
	}
}