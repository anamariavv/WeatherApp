package ui.settings

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import model.common.ErrorData
import ui.base.BaseViewModel
import ui.common.model.CommonMessages
import ui.settings.model.RadioGroupState
import ui.settings.model.RadioItem
import usecase.settings.GetUnitsUseCase
import usecase.settings.ToggleUnitsUseCase
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
	private val toggleUnitsUseCase: ToggleUnitsUseCase,
	private val getUnitsUseCase: GetUnitsUseCase
) : BaseViewModel() {

	private val _unitRadioGroupState = MutableStateFlow(RadioGroupState(listOf(RadioItem.Metric, RadioItem.Imperial), selectedOption = RadioItem.Metric))
	val unitRadioGroupState = _unitRadioGroupState.asStateFlow()

	init {
		showScreenLoading()
		runSuspend { getUnits() }
	}

	private suspend fun getUnits() {
		getUnitsUseCase().onFinished(this::getUnitsSuccess, this::handleErrors)
	}

	private fun getUnitsSuccess(isMetric: Boolean) {
		val item = if (isMetric) RadioItem.Metric else RadioItem.Imperial
		_unitRadioGroupState.update { it.copy(selectedOption = item) }

		showScreenContent()
	}

	fun setSelectedOption(isMetric: Boolean) {
		runSuspend { setSelectedOptionInternal(isMetric) }
	}

	private suspend fun setSelectedOptionInternal(isMetric: Boolean) {
		toggleUnitsUseCase(isMetric).onError(this::handleErrors)

		val item = if (isMetric) RadioItem.Metric else RadioItem.Imperial
		_unitRadioGroupState.update {
			it.copy(selectedOption = item)
		}
	}

	private fun handleErrors(errorData: ErrorData) {
		when (errorData.errorType) {
			ToggleUnitsUseCase.ToggleUnitsUseCaseError.TOGGLE_UNITS_ERROR -> showErrorDialog(CommonMessages.UnexpectedError)
		}
	}
}