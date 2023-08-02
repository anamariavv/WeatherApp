package ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import navigation.Router
import ui.base.model.DialogState
import utils.empty
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

	@Inject
	protected lateinit var router: Router

	private val _dialogState = MutableStateFlow(DialogState(message = String.empty(), isVisible = false))
	val dialogState = _dialogState.asStateFlow()

	protected fun runSuspend(job: suspend () -> Unit) {
		viewModelScope.launch { job() }
	}

	protected fun showError(message: String) {
		_dialogState.update { it.copy(message = message, isVisible = true) }
	}

	protected fun showInfo(message: String) {
		_dialogState.update { it.copy(message = message, isVisible = true) }
	}

	fun onDialogDismissed() {
		_dialogState.update { it.copy(message = String.empty(), isVisible = false) }
	}
}