package model.network.forecast


import com.google.gson.annotations.SerializedName

data class ApiWindDay(
	@SerializedName("Direction")
	val direction: ApiDirection,
	@SerializedName("Speed")
	val speed: ApiSpeed
)