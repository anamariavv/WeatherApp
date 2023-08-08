package ui.cities.model

import model.city.City

data class SearchBarState(
    val queryText: String,
    val isActive: Boolean,
    val isEnabled: Boolean,
    val queryResult: List<City>
)
