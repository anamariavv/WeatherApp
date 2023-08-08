package model.network.forecast


import com.google.gson.annotations.SerializedName

data class ApiRealFeelTemperature(
	@SerializedName("Maximum")
	val maximum: ApiRealFeelMaximum,
	@SerializedName("Minimum")
	val minimum: ApiRealFeelMinimum
)