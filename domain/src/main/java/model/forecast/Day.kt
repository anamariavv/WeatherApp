package model.forecast

data class Day(
	val cloudCover: Int,
	val hasPrecipitation: Boolean,
	val hoursOfIce: Int,
	val hoursOfPrecipitation: Int,
	val hoursOfRain: Int,
	val hoursOfSnow: Int,
	val ice: Measurement,
	val iceProbability: Int,
	val icon: Int,
	val iconPhrase: String,
	val longPhrase: String,
	val precipitationProbability: Int,
	val rain: Measurement,
	val rainProbability: Int,
	val shortPhrase: String,
	val snow: Measurement,
	val snowProbability: Int,
	val thunderstormProbability: Int,
	val totalLiquid: Measurement,
	val wind: Wind,
	val windGust: Wind
)