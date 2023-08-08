package model.network.forecast


import com.google.gson.annotations.SerializedName

data class ApiSun(
	@SerializedName("EpochRise")
	val epochRise: Int,
	@SerializedName("EpochSet")
	val epochSet: Int,
	@SerializedName("Rise")
	val rise: String,
	@SerializedName("Set")
	val `set`: String
)