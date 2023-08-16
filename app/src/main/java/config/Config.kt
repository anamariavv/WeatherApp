package config

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.DateRange
import androidx.compose.material.icons.sharp.Home
import androidx.compose.material.icons.sharp.LocationOn
import androidx.compose.material.icons.sharp.Settings
import com.example.weatherapp.R
import navigation.component.BottomNavItem
import navigation.component.NavigationDirection

object Config {
    const val DATA_STORE_NAME = "preferences"
    const val databaseName = "locationDatabase"
    const val retrofitBaseUrl = "http://dataservice.accuweather.com"

    val BOTTOM_NAV_BAR_ITEMS = listOf(
        BottomNavItem(
            icon = Icons.Sharp.Home,
            contentDescriptionId = R.string.bottom_navbar_home_content_description,
            direction = NavigationDirection.Home
        ),
        BottomNavItem(
            icon = Icons.Sharp.DateRange,
            contentDescriptionId = R.string.bottom_navbar_weekly_content_description,
            direction = NavigationDirection.Weekly
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