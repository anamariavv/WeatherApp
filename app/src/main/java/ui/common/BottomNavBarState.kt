package ui.common

import navigation.BottomNavItem

data class BottomNavBarState(
    val isVisible: Boolean,
    val onItemClicked: (BottomNavItem) -> Unit,
    val items: List<BottomNavItem>,
    val selectedItem: BottomNavItem
)