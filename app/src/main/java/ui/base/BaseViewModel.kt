package ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import navigation.Router
import ui.base.model.BaseScreenState
import ui.base.model.DialogState
import ui.common.model.Message
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

	@Inject
	protected lateinit var router: Router

	private val _dialogState = MutableStateFlow<DialogState>(DialogState.None())
	val dialogState = _dialogState.asStateFlow()

	private val _screenState = MutableStateFlow<BaseScreenState>(BaseScreenState.Content())
	val screenState = _screenState.asStateFlow()

	protected fun runSuspend(job: suspend () -> Unit) {
		viewModelScope.launch { job() }
	}

	protected fun showErrorDialog(message: Message) {
		_dialogState.update { DialogState.Error(message = message) }
	}

	protected fun showInfoDialog(message: Message) {
		_dialogState.update { DialogState.Info(message = message) }
	}

	protected fun showLoadingDialog() {
		_dialogState.update { DialogState.Loading() }
	}

	fun dismissDialog() {
		_dialogState.update { DialogState.None() }
	}

	protected fun showScreenLoading() {
		_screenState.update { BaseScreenState.Loading() }
	}

	protected fun showScreenContent() {
		_screenState.update { BaseScreenState.Content() }
	}

	protected fun showScreenNoContent() {
		_screenState.update { BaseScreenState.NoContent() }
	}
}