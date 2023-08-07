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
    suspend fun getAll(): List<FavouriteCity>

    @Insert
    suspend fun addFavouriteCity(city: FavouriteCity)

    @Delete
    suspend fun removeFavouriteCity(city: FavouriteCity)
}