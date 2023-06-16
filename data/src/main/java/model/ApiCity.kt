package model


import com.google.gson.annotations.SerializedName

data class ApiCity(
    @SerializedName("Country")
    val country: ApiCountry,
    @SerializedName("Key")
    val key: String,
    @SerializedName("LocalizedName")
    val localizedName: String,
    @SerializedName("Rank")
    val rank: Int,
    @SerializedName("Type")
    val type: String
)