package model.forecast

import model.forecast.Measurement
import model.forecast.Wind

data class CurrentConditions(
	val epochTime: Int,
	val hasPrecipitation: Boolean,
	val isDayTime: Boolean,
	val localObservationDateTime: String,
	val mobileLink: String,
	val obstructionsToVisibility: String,
	val precipitationType: Any,
	val pressure: Measurement,
	val realFeelTemperature: Measurement,
	val realFeelTemperatureShade: Measurement,
	val relativeHumidity: Int,
	val temperature: Measurement,
	val uVIndex: Int,
	val uVIndexText: String,
	val visibility: Measurement,
	val weatherText: String,
	val wind: Wind,
	val windGust: Wind
)