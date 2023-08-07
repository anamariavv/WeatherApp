package model

data class City(
    val localizedName: String,
    val locationKey: String,
    val countryId: String,
    val countryLocalizedName: String,
    val rank: Int,
    val type: String,
    val isFavourite: Boolean
)
