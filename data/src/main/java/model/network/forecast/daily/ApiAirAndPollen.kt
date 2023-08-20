package model.network.forecast.daily


import com.google.gson.annotations.SerializedName

data class ApiAirAndPollen(
	@SerializedName("Category")
	val category: String,
	@SerializedName("CategoryValue")
	val categoryValue: Int,
	@SerializedName("Name")
	val name: String,
	@SerializedName("Type")
	val type: String,
	@SerializedName("Value")
	val value: Int
)