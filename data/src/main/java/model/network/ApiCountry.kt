package model.network

import com.google.gson.annotations.SerializedName

data class ApiCountry(
    @SerializedName("ID")
    val id: String,
    @SerializedName("LocalizedName")
    val localizedName: String
)