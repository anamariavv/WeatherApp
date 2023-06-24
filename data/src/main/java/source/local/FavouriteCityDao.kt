package source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import model.local.FavouriteCity
import model.local.FavouriteCityWithCountry

@Dao
interface FavouriteCityDao {
    @Transaction
    @Query("SELECT * FROM favouriteCity")
    fun getAll(): List<FavouriteCityWithCountry>

    @Insert
    fun addFavouriteCity(favouriteCity: FavouriteCity)

    @Delete
    fun removeFavouriteCity(favouriteCity: FavouriteCity)
}