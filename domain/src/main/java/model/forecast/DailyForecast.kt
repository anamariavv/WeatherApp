package model.forecast

data class DailyForecast(
	val airAndPollen: List<AirAndPollen>,
	val date: String,
	val day: Day,
	val icon: Int,
	val epochDate: Long,
	val hoursOfSun: Double,
	val mobileLink: String,
	val moonRiseEpoch: Int,
	val moonSetEpoch: Int,
	val sunRiseEpoch: Int,
	val sunSetEpoch: Int,
	val night: Night,
	val realFeelTemperature: Temperature,
	val realFeelTemperatureShade: Temperature,
	val temperature: Temperature
)