package model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavouriteCountry(
    @PrimaryKey val id: String,
    val localizedName: String
)