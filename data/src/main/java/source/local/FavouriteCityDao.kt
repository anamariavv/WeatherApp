package source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import model.local.FavouriteCity

@Dao
interface FavouriteCityDao {
    @Transaction
    @Query("SELECT * FROM FavouriteCity")
    fun getAll(): List<FavouriteCity>

    @Insert
    fun addFavouriteCity(city: FavouriteCity)

    @Delete
    fun removeFavouriteCity(city: FavouriteCity)
}