package model.network.forecast.daily


import com.google.  gson.annotations.SerializedName

data class ApiDailyForecast(
	@SerializedName("Date")
	val date: String,
	@SerializedName("Day")
	val day: ApiDay,
	@SerializedName("Icon")
	val icon: Int,
	@SerializedName("EpochDate")
	val epochDate: Long,
	@SerializedName("MobileLink")
	val mobileLink: String,
	@SerializedName("Temperature")
	val temperature: ApiTemperature
)