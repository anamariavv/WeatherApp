package config

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Home
import androidx.compose.material.icons.sharp.LocationOn
import androidx.compose.material.icons.sharp.Settings
import com.example.weatherapp.R
import navigation.component.BottomNavItem
import navigation.component.NavigationDirection

object Config {
	const val dataStoreName = "preferences"
	const val databaseName = "locationDatabase"
	const val retrofitBaseUrl = "http://dataservice.accuweather.com"
	const val notificationChannelId = "forecastNotificationChannel"
	const val notificationChannelName = "forecast"
	const val notificationChannelDescription = "A notification channel for weather updates"

	val BOTTOM_NAV_BAR_ITEMS = listOf(
		BottomNavItem(
			icon = Icons.Sharp.Home,
			contentDescriptionId = R.string.bottom_navbar_home_content_description,
			direction = NavigationDirection.Home
		),
		BottomNavItem(
			icon = Icons.Sharp.Settings,
			contentDescriptionId = R.string.bottom_navbar_settings_content_description,
			direction = NavigationDirection.Settings
		),
		BottomNavItem(
			icon = Icons.Sharp.LocationOn,
			contentDescriptionId = R.string.bottom_navbar_cities_content_description,
			direction = NavigationDirection.Cities
		),
	)
}