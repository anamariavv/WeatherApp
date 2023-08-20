package ui.cities.model

import com.example.weatherapp.R
import ui.common.model.Message

sealed class CityScreenMessages(vararg val args: String) : Message {
	object AddFavouriteCityError : CityScreenMessages()
	object RemoveFavouriteCityError : CityScreenMessages()
	object GetFavouritesError : CityScreenMessages()
	object QueryCitiesError : CityScreenMessages()
	class LocationResultInfo(city: String, country: String) : CityScreenMessages(args = arrayOf(city, country))
	object LocationPermissionNeeded : CityScreenMessages()

	override fun getTitleId(): Int {
		return when (this) {
			is AddFavouriteCityError -> R.string.cities_screen_error_adding_to_favourites_title
			is RemoveFavouriteCityError -> R.string.cities_screen_error_removing_from_favourites_title
			is GetFavouritesError -> R.string.cities_screen_error_getting_favourites_title
			is QueryCitiesError -> R.string.cities_screen_error_querying_cities_title
			is LocationResultInfo -> R.string.cities_screen_location_result_title
			is LocationPermissionNeeded -> R.string.cities_screen_location_permission_dialog_title
		}
	}

	override fun getMessageId(): Int {
		return when (this) {
			is AddFavouriteCityError -> R.string.cities_screen_error_adding_to_favourites_message
			is RemoveFavouriteCityError -> R.string.cities_screen_error_removing_from_favourites_message
			is GetFavouritesError -> R.string.cities_screen_error_getting_favourites_message
			is QueryCitiesError -> R.string.cities_screen_error_querying_cities_message
			is LocationResultInfo -> R.string.cities_screen_location_result_message
			is LocationPermissionNeeded -> R.string.cities_screen_location_permission_dialog_message
		}
	}

	override fun getArguments(): List<String>? {
		return when (this) {
			is LocationResultInfo -> args.asList()
			else -> { null }
		}
	}
}