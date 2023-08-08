package model.network.forecast


import com.google.gson.annotations.SerializedName

data class ApiRealFeelTemperatureShade(
	@SerializedName("Maximum")
	val maximum: ApiRealFeelMaximum,
	@SerializedName("Minimum")
	val minimum: ApiRealFeelMeasure
)