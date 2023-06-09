package config

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.DateRange
import androidx.compose.material.icons.sharp.Home
import androidx.compose.material.icons.sharp.Settings
import com.example.weatherapp.R
import navigation.BottomNavItem
import navigation.NavigationDirection

object Config {

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
        )
    )
}