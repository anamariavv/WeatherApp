package ui.cities.model

import com.example.weatherapp.R
import ui.common.model.Message

enum class CityScreenMessage(vararg val args: String) : Message {
	ADD_FAVOURITE_CITY_ERROR,
	REMOVE_FAVOURITE_CITY_ERROR,
	GET_FAVOURITES_ERROR,
	QUERY_CITIES_ERROR,
	GET_LOCATION_ERROR,
	LOCATION_RESULT_INFO,
	LOCATION_PERMISSION_NEEDED;

	override fun getTitleId(): Int {
		return when (this) {
			ADD_FAVOURITE_CITY_ERROR -> R.string.cities_screen_error_adding_to_favourites_title
			REMOVE_FAVOURITE_CITY_ERROR -> R.string.cities_screen_error_removing_from_favourites_title
			GET_FAVOURITES_ERROR -> R.string.cities_screen_error_getting_favourites_title
			QUERY_CITIES_ERROR -> R.string.cities_screen_error_querying_cities_title
			GET_LOCATION_ERROR -> R.string.cities_screen_error_getting_location_title
			LOCATION_RESULT_INFO -> R.string.cities_screen_location_result_title
			LOCATION_PERMISSION_NEEDED -> R.string.cities_screen_location_permission_dialog_title
		}
	}

	override fun getMessageId(): Int {
		return when (this) {
			ADD_FAVOURITE_CITY_ERROR -> R.string.cities_screen_error_adding_to_favourites_message
			REMOVE_FAVOURITE_CITY_ERROR -> R.string.cities_screen_error_removing_from_favourites_message
			GET_FAVOURITES_ERROR -> R.string.cities_screen_error_getting_favourites_message
			QUERY_CITIES_ERROR -> R.string.cities_screen_error_querying_cities_message
			GET_LOCATION_ERROR -> R.string.cities_screen_error_getting_location_message
			LOCATION_RESULT_INFO -> R.string.cities_screen_location_result_message
			LOCATION_PERMISSION_NEEDED -> R.string.cities_screen_location_permission_dialog_message
		}
	}

	override fun getArguments(): List<String>? {
		return when(this) {
			LOCATION_RESULT_INFO -> args.asList()
			else -> {null}
		}
	}
}