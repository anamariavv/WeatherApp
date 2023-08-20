package navigation.component

import androidx.compose.ui.graphics.vector.ImageVector
import navigation.NavigationCommand

data class BottomNavItem(
    val icon: ImageVector,
    val contentDescriptionId: Int,
    val direction: NavigationCommand
)