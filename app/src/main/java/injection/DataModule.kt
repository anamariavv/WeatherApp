package injection

import com.google.android.gms.location.FusedLocationProviderClient
import config.Config
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import interactor.AddFavouriteCityInteractor
import interactor.GetCityBasedOnCoordsInteractor
import interactor.GetCurrentGeoLocationInteractor
import interactor.impl.AddFavouriteCityInteractorImpl
import interactor.GetFavouriteCitiesInteractor
import interactor.impl.GetFavouriteCitiesInteractorImpl
import interactor.QueryCitiesInteractor
import interactor.impl.QueryCitiesInteractorImpl
import interactor.RemoveFavouriteCityInteractor
import interactor.impl.GetCityBasedOnCoordsInteractorImpl
import interactor.impl.GetCurrentGeoLocationInteractorImpl
import interactor.impl.RemoveFavouriteCityInteractorImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import source.local.FavouriteCityDao
import source.local.GeoLocationProvider
import source.local.LocationDatabase
import source.local.impl.GeoLocationProviderImpl
import source.network.LocationsService

@Module
@InstallIn(ViewModelComponent::class)
class DataModule {

    @Provides
    @ViewModelScoped
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @ViewModelScoped
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
        return Retrofit.Builder().baseUrl(Config.retrofitBaseUrl)
            .addConverterFactory(gsonConverterFactory).client(okHttpClient).build()
    }

    @Provides
    @ViewModelScoped
    fun provideFavouriteCityWithCountryDao(locationDatabase: LocationDatabase): FavouriteCityDao {
        return locationDatabase.favouriteCityDao()
    }

    @Provides
    @ViewModelScoped
    fun provideLocationService(retrofit: Retrofit): LocationsService {
        return retrofit.create(LocationsService::class.java)
    }


    @Provides
    @ViewModelScoped
    fun provideQueryCitiesInteractor(locationsService: LocationsService): QueryCitiesInteractor {
        return QueryCitiesInteractorImpl(locationsService)
    }

    @Provides
    @ViewModelScoped
    fun provideGetFavouriteCitiesInteractor(favouriteCityDao: FavouriteCityDao): GetFavouriteCitiesInteractor {
        return GetFavouriteCitiesInteractorImpl(favouriteCityDao)
    }

    @Provides
    @ViewModelScoped
    fun provideAddFavouriteCityInteractor(favouriteCityDao: FavouriteCityDao): AddFavouriteCityInteractor {
        return AddFavouriteCityInteractorImpl(favouriteCityDao)
    }

    @Provides
    @ViewModelScoped
    fun provideRemoveFavouriteCityInteractor(favouriteCityDao: FavouriteCityDao): RemoveFavouriteCityInteractor {
        return RemoveFavouriteCityInteractorImpl(favouriteCityDao)
    }

    @Provides
    @ViewModelScoped
    fun provideGetCurrentGeoLocationInteractor(geoLocationProvider: GeoLocationProvider): GetCurrentGeoLocationInteractor {
        return GetCurrentGeoLocationInteractorImpl(geoLocationProvider)
    }

    @Provides
    @ViewModelScoped
    fun provideGeoLocationProvider(fusedLocationProviderClient: FusedLocationProviderClient): GeoLocationProvider {
        return GeoLocationProviderImpl(fusedLocationProviderClient)
    }

    @Provides
    @ViewModelScoped
    fun provideGetCityBasedOnCoordsInteractor(locationsService: LocationsService): GetCityBasedOnCoordsInteractor {
        return GetCityBasedOnCoordsInteractorImpl(locationsService)
    }
}