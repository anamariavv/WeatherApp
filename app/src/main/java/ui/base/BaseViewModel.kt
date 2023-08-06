package ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import navigation.Router
import ui.base.model.DialogState
import ui.common.model.Message
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

	@Inject
	protected lateinit var router: Router

	private val _dialogState = MutableStateFlow<DialogState>(DialogState.None())
	val dialogState = _dialogState.asStateFlow()

	protected fun runSuspend(job: suspend () -> Unit) {
		viewModelScope.launch { job() }
	}

	protected fun showError(message: Message) {
		_dialogState.update { DialogState.Error(message = message) }
	}

	protected fun showInfo(message: Message) {
		_dialogState.update { DialogState.Info(message = message) }
	}

	protected fun showLoading() {
		_dialogState.update { DialogState.Loading() }
	}

	fun dismissDialog() {
		_dialogState.update { DialogState.None() }
	}
}