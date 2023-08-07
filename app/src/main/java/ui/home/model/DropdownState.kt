package ui.home.model

import model.City

data class DropdownState(val isExpanded: Boolean, val list: List<City>, val selectedIndex: Int, val selectedValue: City?)