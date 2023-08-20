package model.network.forecast.daily


import com.google.gson.annotations.SerializedName

data class ApiDailyForecast(
	@SerializedName("AirAndPollen")
	val airAndPollen: List<ApiAirAndPollen>,
	@SerializedName("Date")
	val date: String,
	@SerializedName("Day")
	val day: ApiDay,
	@SerializedName("EpochDate")
	val epochDate: Int,
	@SerializedName("HoursOfSun")
	val hoursOfSun: Double,
	@SerializedName("MobileLink")
	val mobileLink: String,
	@SerializedName("Moon")
	val moon: ApiMoon,
	@SerializedName("Night")
	val night: ApiNight,
	@SerializedName("RealFeelTemperature")
	val realFeelTemperature: ApiRealFeelTemperature,
	@SerializedName("RealFeelTemperatureShade")
	val realFeelTemperatureShade: ApiRealFeelTemperature,
	@SerializedName("Sources")
	val sources: List<String>,
	@SerializedName("Sun")
	val sun: ApiSun,
	@SerializedName("Temperature")
	val temperature: ApiTemperature
)