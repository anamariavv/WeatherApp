package model.network.forecast.daily


import com.google.gson.annotations.SerializedName

data class ApiMeasurement(
	@SerializedName("Phrase")
	val phrase: String?,
	@SerializedName("Unit")
	val unit: String,
	@SerializedName("UnitType")
	val unitType: Int,
	@SerializedName("Value")
	val value: Double?
)