package model.network.forecast


import com.google.gson.annotations.SerializedName

data class ApiRealFeelTemperature(
	@SerializedName("Maximum")
	val maximum: ApiRealFeelMeasure,
	@SerializedName("Minimum")
	val minimum: ApiRealFeelMeasure
)