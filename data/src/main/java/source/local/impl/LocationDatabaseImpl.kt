package source.local.impl

import androidx.room.Database
import androidx.room.RoomDatabase
import model.local.city.FavouriteCity
import source.local.FavouriteCityDao
import source.local.LocationDatabase

@Database(entities = [FavouriteCity::class], version = 1)
abstract class LocationDatabaseImpl : LocationDatabase, RoomDatabase() {
    abstract override fun favouriteCityDao(): FavouriteCityDao
}