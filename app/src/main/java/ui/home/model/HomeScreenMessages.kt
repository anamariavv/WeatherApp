package ui.home.model

import com.example.weatherapp.R
import ui.common.model.Message

sealed class HomeScreenMessages : Message {

	object GetFavouriteCitiesError : HomeScreenMessages()

	object GetForecastError : HomeScreenMessages()

	object CityListInfo : HomeScreenMessages()

	override fun getTitleId(): Int {
		return when (this) {
			is GetFavouriteCitiesError -> R.string.home_screen_get_favourites_error_title
			is GetForecastError -> R.string.home_screen_get_forecast_title
			is CityListInfo -> R.string.home_screen_city_list_info_title
		}
	}

	override fun getMessageId(): Int {
		return when (this) {
			is GetFavouriteCitiesError -> R.string.home_screen_get_favourites_error_message
			is GetForecastError -> R.string.home_screen_get_forecast_message
			is CityListInfo -> R.string.home_screen_city_list_info_message
		}
	}

}
