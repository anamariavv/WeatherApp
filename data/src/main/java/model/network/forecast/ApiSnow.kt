package model.network.forecast


import com.google.gson.annotations.SerializedName

data class ApiSnow(
	@SerializedName("Unit")
	val unit: String,
	@SerializedName("UnitType")
	val unitType: Int,
	@SerializedName("Value")
	val value: Int
)