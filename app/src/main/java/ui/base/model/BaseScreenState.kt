package ui.base.model

sealed class BaseScreenState {
	object Content : BaseScreenState()

	object Loading : BaseScreenState()

	object NoContent : BaseScreenState()
}
