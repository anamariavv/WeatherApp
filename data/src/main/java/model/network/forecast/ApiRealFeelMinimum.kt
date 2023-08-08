package model.network.forecast


import com.google.gson.annotations.SerializedName

data class ApiRealFeelMinimum(
	@SerializedName("Phrase")
	val phrase: String,
	@SerializedName("Unit")
	val unit: String,
	@SerializedName("UnitType")
	val unitType: Int,
	@SerializedName("Value")
	val value: Int
)