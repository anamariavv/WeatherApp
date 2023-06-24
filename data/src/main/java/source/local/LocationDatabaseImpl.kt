package source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import model.local.FavouriteCity

@Database(entities = [FavouriteCity::class], version = 1)
abstract class LocationDatabaseImpl : LocationDatabase, RoomDatabase() {
    abstract override fun favouriteCityDao(): FavouriteCityDao
}