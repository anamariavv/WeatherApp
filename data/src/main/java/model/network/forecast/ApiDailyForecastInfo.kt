package model.network.forecast


import com.google.gson.annotations.SerializedName

data class ApiDailyForecastInfo(
	@SerializedName("DailyForecasts")
	val dailyForecasts: List<ApiDailyForecast>,
	@SerializedName("Headline")
	val headline: ApiHeadline
)