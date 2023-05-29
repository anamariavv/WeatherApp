package navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val title: String, val icon: ImageVector, var route: String) {
    object Home : BottomNavItem("Home", Icons.Rounded.Home, "home")
    object Weekly : BottomNavItem("Weekly",  Icons.Rounded.DateRange, "weekly")
    object Settings : BottomNavItem("Settings", Icons.Rounded.Settings,"settings")
}