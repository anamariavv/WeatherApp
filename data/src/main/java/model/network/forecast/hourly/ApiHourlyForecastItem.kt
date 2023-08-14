package model.network.forecast.hourly


import com.google.gson.annotations.SerializedName
import model.network.forecast.daily.ApiMeasurement

data class ApiHourlyForecastItem(
    @SerializedName("DateTime")
    val dateTime: String,
    @SerializedName("EpochDateTime")
    val epochDateTime: Int,
    @SerializedName("HasPrecipitation")
    val hasPrecipitation: Boolean,
    @SerializedName("IconPhrase")
    val iconPhrase: String,
    @SerializedName("IsDaylight")
    val isDaylight: Boolean,
    @SerializedName("MobileLink")
    val mobileLink: String,
    @SerializedName("PrecipitationIntensity")
    val precipitationIntensity: String,
    @SerializedName("PrecipitationProbability")
    val precipitationProbability: Int,
    @SerializedName("PrecipitationType")
    val precipitationType: String,
    @SerializedName("Temperature")
    val temperature: ApiMeasurement
)