package model.network.forecast


import com.google.gson.annotations.SerializedName

data class ApiTemperature(
	@SerializedName("Maximum")
	val maximum: ApiMeasure,
	@SerializedName("Minimum")
	val minimum: ApiMeasure
)