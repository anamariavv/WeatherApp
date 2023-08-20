package model.network.forecast.current


import com.google.gson.annotations.SerializedName
import model.network.forecast.daily.ApiMeasurement

data class ApiPressureCurrent(
    @SerializedName("Imperial")
    val imperial: ApiMeasurement,
    @SerializedName("Metric")
    val metric: ApiMeasurement
)