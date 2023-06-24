package model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavouriteCity(
    @PrimaryKey val locationKey: String,
    val localizedName: String,
    val rank: Int,
    val type: String,
    val countryId: String
)