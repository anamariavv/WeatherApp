package model.local

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

@Entity
data class FavouriteCityWithCountry(
    @Embedded val city: FavouriteCity,
    @Relation(parentColumn = "countryId", entityColumn = "id")
    val country: FavouriteCountry
)