package navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.DateRange
import androidx.compose.material.icons.sharp.Home
import androidx.compose.material.icons.sharp.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.weatherapp.R

sealed class BottomNavItem(val icon: ImageVector, val contentDescriptionId: Int, val route: String) {
    object Home : BottomNavItem(Icons.Sharp.Home, R.string.bottom_navbar_home_content_description,"route_home")
    object Weekly : BottomNavItem(Icons.Sharp.DateRange, R.string.bottom_navbar_weekly_content_description,"route_weekly")
    object Settings : BottomNavItem(Icons.Sharp.Settings, R.string.bottom_navbar_settings_content_description,"route_settings")
}