package ui.home.model

import java.util.*

data class UiHourlyForecastItem(
	val time: String,
	val iconPhrase: String,
	val weatherIconId: Int,
	val temperature: String
)
