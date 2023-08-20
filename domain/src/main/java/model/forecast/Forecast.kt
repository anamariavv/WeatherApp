package model.forecast

data class Forecast(
	val dailyForecasts: List<DailyForecast>,
	val headline: Headline
)