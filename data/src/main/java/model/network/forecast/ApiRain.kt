package model.network.forecast


import com.google.gson.annotations.SerializedName

data class ApiRain(
	@SerializedName("Unit")
	val unit: String,
	@SerializedName("UnitType")
	val unitType: Int,
	@SerializedName("Value")
	val value: Double
)