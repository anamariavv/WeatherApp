package model.network.forecast.daily


import com.google.gson.annotations.SerializedName

data class ApiRealFeelTemperature(
	@SerializedName("Maximum")
	val maximum: ApiRealFeelMeasure,
	@SerializedName("Minimum")
	val minimum: ApiRealFeelMeasure
)