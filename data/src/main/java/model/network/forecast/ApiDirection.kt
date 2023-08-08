package model.network.forecast


import com.google.gson.annotations.SerializedName

data class ApiDirection(
	@SerializedName("Degrees")
	val degrees: Int,
	@SerializedName("English")
	val english: String,
	@SerializedName("Localized")
	val localized: String
)