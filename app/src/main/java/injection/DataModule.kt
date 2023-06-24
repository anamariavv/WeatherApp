package injection

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import interactor.AddFavouriteCityInteractor
import interactor.AddFavouriteCityInteractorImpl
import interactor.GetFavouriteCitiesInteractor
import interactor.GetFavouriteCitiesInteractorImpl
import interactor.QueryCitiesInteractor
import interactor.QueryCitiesInteractorImpl
import interactor.RemoveFavouriteCityInteractor
import interactor.RemoveFavouriteCityInteractorImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import source.local.FavouriteCityDao
import source.local.LocationDatabase
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
        return Retrofit.Builder().baseUrl("http://dataservice.accuweather.com")
            .addConverterFactory(gsonConverterFactory).client(okHttpClient).build()
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
    fun provideFavouriteCityWithCountryDao(locationDatabase: LocationDatabase): FavouriteCityDao {
        return locationDatabase.favouriteCityDao()
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
}