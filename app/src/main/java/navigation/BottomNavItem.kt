package navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val icon: ImageVector,
    val contentDescriptionId: Int,
    val direction: NavigationDirection
)