package model.network.forecast.daily


import com.google.gson.annotations.SerializedName

data class ApiForecast(
	@SerializedName("DailyForecasts")
	val dailyForecasts: List<ApiDailyForecast>,
	@SerializedName("Headline")
	val headline: ApiHeadline
)