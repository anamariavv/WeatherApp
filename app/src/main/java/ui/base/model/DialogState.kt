package ui.base.model

import ui.common.model.Message

sealed class DialogState(val message: Message?) {
	class None : DialogState(message = null)

	class Loading : DialogState(message = null)

	class Info(message: Message) : DialogState(message = message)

	class Error(message: Message) : DialogState(message = message)
}
