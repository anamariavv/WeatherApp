package model.network.forecast.daily


import com.google.gson.annotations.SerializedName

data class ApiWind(
	@SerializedName("Direction")
	val direction: ApiDirection?,
	@SerializedName("Speed")
	val speed: ApiMeasurement
)