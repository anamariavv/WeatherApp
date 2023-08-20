package ui.home.model

import com.example.weatherapp.R
import utils.empty

data class UiCurrentConditions(
	val isDayTime: Boolean,
	val mobileLink: String,
	val hasPrecipitation: Boolean,
	val precipitationType: String?,
	val temperature: String,
	val realFeelTemperature: String,
	val weatherText: String,
	val weatherIconId: Int,
	val hasWind: Boolean,
	val windSpeed: String?,
	val hasHumidity: Boolean,
	val humidity: String?,
	val hasUvIndex: Boolean,
	val uvIndex: String?,
) {
	constructor() : this(
		false, String.empty(), false, String.empty(), String.empty(), String.empty(), String.empty(), R.drawable.day,
		false, String.empty(), false, String.empty(), false, String.empty()
	)
}