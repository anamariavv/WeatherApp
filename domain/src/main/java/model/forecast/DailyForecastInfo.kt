package model.forecast

data class DailyForecastInfo(
	val dailyForecasts: List<DailyForecast>,
	val headline: Headline
)