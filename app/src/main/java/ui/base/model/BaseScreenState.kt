package ui.base.model

sealed class BaseScreenState() {
	class Content : BaseScreenState()

	class Loading : BaseScreenState()

	class NoContent : BaseScreenState()
}
