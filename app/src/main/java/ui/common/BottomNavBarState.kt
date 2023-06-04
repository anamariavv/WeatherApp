package ui.common

import navigation.BottomNavItem
import navigation.NavigationEvent

data class BottomNavBarState(
    val isVisible: Boolean,
    val onItemClicked: (NavigationEvent) -> Unit,
    val items: List<BottomNavItem>
)