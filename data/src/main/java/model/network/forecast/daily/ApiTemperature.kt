package model.network.forecast.daily


import com.google.gson.annotations.SerializedName

data class ApiTemperature(
	@SerializedName("Maximum")
	val maximum: ApiMeasurement,
	@SerializedName("Minimum")
	val minimum: ApiMeasurement
)