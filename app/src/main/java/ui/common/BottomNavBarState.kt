package ui.common

import navigation.component.BottomNavItem

data class BottomNavBarState(
    val isVisible: Boolean,
    val onItemClicked: (BottomNavItem) -> Unit,
    val items: List<BottomNavItem>,
    val selectedItem: BottomNavItem
)