package model.forecast

data class HourlyForecastItem(
	val dateTime: String,
	val epochDateTime: Int,
	val hasPrecipitation: Boolean,
	val iconPhrase: String,
	val isDaylight: Boolean,
	val mobileLink: String,
	val precipitationIntensity: String?,
	val precipitationProbability: Int?,
	val precipitationType: String?,
	val temperature: Measurement
)