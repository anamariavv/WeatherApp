package model.network.forecast.current


import com.google.gson.annotations.SerializedName

data class ApiCurrentConditionsItem(
    @SerializedName("EpochTime")
    val epochTime: Int,
    @SerializedName("HasPrecipitation")
    val hasPrecipitation: Boolean,
    @SerializedName("IsDayTime")
    val isDayTime: Boolean,
    @SerializedName("LocalObservationDateTime")
    val localObservationDateTime: String,
    @SerializedName("MobileLink")
    val mobileLink: String,
    @SerializedName("ObstructionsToVisibility")
    val obstructionsToVisibility: String,
    @SerializedName("PrecipitationType")
    val precipitationType: Any,
    @SerializedName("Pressure")
    val pressure: ApiPressureCurrent,
    @SerializedName("RealFeelTemperature")
    val realFeelTemperature: ApiRealFeelTemperatureCurrent,
    @SerializedName("RealFeelTemperatureShade")
    val realFeelTemperatureShade: ApiRealFeelTemperatureCurrent,
    @SerializedName("RelativeHumidity")
    val relativeHumidity: Int,
    @SerializedName("Temperature")
    val temperature: ApiTemperatureCurrent,
    @SerializedName("UVIndex")
    val uVIndex: Int,
    @SerializedName("UVIndexText")
    val uVIndexText: String,
    @SerializedName("Visibility")
    val visibility: ApiVisibilityCurrent,
    @SerializedName("WeatherText")
    val weatherText: String,
    @SerializedName("Wind")
    val wind: ApiWindCurrent,
    @SerializedName("WindGust")
    val windGust: ApiWindCurrent
)