package ui.common.model

import com.example.weatherapp.R

sealed class CommonMessages : Message {

	object GetLocationError : CommonMessages()

	object UnexpectedError : CommonMessages()

	override fun getTitleId(): Int {
		return when (this) {
			is GetLocationError -> R.string.common_error_getting_location_title
			is UnexpectedError -> R.string.common_unexpected_error_title
		}
	}

	override fun getMessageId(): Int {
		return when (this) {
			is GetLocationError -> R.string.common_error_getting_location_message
			is UnexpectedError -> R.string.common_unexpected_error_message
		}
	}
}
