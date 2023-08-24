package model.forecast

data class DailyForecast(
	val date: String,
	val day: Day,
	val icon: Int,
	val epochDate: Long,
	val mobileLink: String,
	val temperature: Temperature
)