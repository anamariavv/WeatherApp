package ui.common.model

interface Message {

	fun getTitleId(): Int

	fun getMessageId(): Int

	fun getArguments(): List<String>? = null
}