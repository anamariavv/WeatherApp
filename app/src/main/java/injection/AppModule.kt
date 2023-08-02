package injection

import android.content.Context
import androidx.room.Room
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import config.Config
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import navigation.NavigationDelegate
import navigation.impl.NavigationDelegateImpl
import navigation.Router
import navigation.impl.RouterImpl
import source.local.LocationDatabase
import source.local.impl.LocationDatabaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRouter(navigationDelegate: NavigationDelegate): Router =
        RouterImpl(navigationDelegate)

    @Provides
    @Singleton
    fun provideNavigationDelegate(): NavigationDelegate = NavigationDelegateImpl()

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext appContext: Context): LocationDatabase {
        return Room.databaseBuilder(
            appContext,
            LocationDatabaseImpl::class.java,
            Config.databaseName
        ).build()
    }

    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(@ApplicationContext appContext: Context): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(appContext)
    }
}