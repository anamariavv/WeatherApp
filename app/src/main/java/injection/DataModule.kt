package injection

import com.google.android.gms.location.FusedLocationProviderClient
import config.Config
import config.api.ApiConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import interactor.*
import interactor.impl.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import source.local.FavouriteCityDao
import source.local.GeoLocationProvider
import source.local.LocationDatabase
import source.local.impl.GeoLocationProviderImpl
import source.network.ForecastService
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
	fun provideForecastService(retrofit: Retrofit): ForecastService {
		return retrofit.create(ForecastService::class.java)
	}


	@Provides
	@ViewModelScoped
	fun provideQueryCitiesInteractor(locationsService: LocationsService): QueryCitiesInteractor {
		return QueryCitiesInteractorImpl(ApiConfig.apiKey, locationsService)
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
	fun provideGetCityBasedOnCoordsInteractor(locationsService: LocationsService): GetCityBasedOnCoordinatesInteractor {
		return GetCityBasedOnCoordinatesInteractorImpl(ApiConfig.apiKey, locationsService)
	}

	@Provides
	@ViewModelScoped
	fun provideGetDailyForecastInteractor(forecastService: ForecastService): GetDailyForecastInteractor {
		return GetDailyForecastInteractorImpl(ApiConfig.apiKey, forecastService)
	}
}