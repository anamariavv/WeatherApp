package model.network.forecast.current


import com.google.gson.annotations.SerializedName
import model.network.forecast.daily.ApiDirection

data class ApiWindCurrent(
    @SerializedName("Direction")
    val direction: ApiDirection,
    @SerializedName("Speed")
    val speed: ApiSpeedCurrent
)