package model

data class City(
    val localizedName: String,
    val locationKey: String,
    val country: Country,
    val rank: Int,
    val type: String
)
