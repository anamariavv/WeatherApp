package injection

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.google.android.gms.location.FusedLocationProviderClient
import config.Config
import config.api.ApiConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import interactor.*
import interactor.city.AddFavouriteCityInteractor
import interactor.city.GetCityBasedOnCoordinatesInteractor
import interactor.city.GetCurrentGeoLocationInteractor
import interactor.city.GetFavouriteCitiesInteractor
import interactor.city.GetSelectedCityLocationKeyInteractor
import interactor.city.QueryCitiesInteractor
import interactor.city.RemoveFavouriteCityInteractor
import interactor.city.SetSelectedCityLocationKeyInteractor
import interactor.city.impl.AddFavouriteCityInteractorImpl
import interactor.city.impl.GetCityBasedOnCoordinatesInteractorImpl
import interactor.city.impl.GetCurrentGeoLocationInteractorImpl
import interactor.city.impl.GetFavouriteCitiesInteractorImpl
import interactor.city.impl.GetSelectedCityLocationKeyInteractorImpl
import interactor.city.impl.QueryCitiesInteractorImpl
import interactor.city.impl.RemoveFavouriteCityInteractorImpl
import interactor.city.impl.SetSelectedCityLocationKeyInteractorImpl
import interactor.forecast.GetCurrentConditionsInteractor
import interactor.forecast.GetDailyForecastInteractor
import interactor.forecast.GetTwelveHourForecastInteractor
import interactor.forecast.GetWeeklyForecastInteractor
import interactor.forecast.impl.GetCurrentConditionsInteractorImpl
import interactor.forecast.impl.GetDailyForecastInteractorImpl
import interactor.forecast.impl.GetTwelveHourForecastInteractorImpl
import interactor.forecast.impl.GetWeeklyForecastInteractorImpl
import interactor.settings.GetLocationPermissionStateInteractor
import interactor.settings.GetUnitsInteractor
import interactor.settings.ToggleLocationPermissionStateInteractor
import interactor.settings.ToggleUnitsInteractor
import interactor.settings.impl.GetLocationPermissionStateInteractorImpl
import interactor.settings.impl.GetUnitsInteractorImpl
import interactor.settings.impl.ToggleLocationPermissionStateInteractorImpl
import interactor.settings.impl.ToggleUnitsInteractorImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import source.local.ApplicationStorage
import source.local.FavouriteCityDao
import source.local.GeoLocationProvider
import source.local.LocationDatabase
import source.local.impl.ApplicationStorageImpl
import source.local.impl.GeoLocationProviderImpl
import source.network.ForecastService
import source.network.LocationsService

val Context.dataStore by preferencesDataStore(Config.dataStoreName)

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
	fun provideApplicationStorage(@ApplicationContext context: Context): ApplicationStorage {
		return ApplicationStorageImpl(context.dataStore)
	}

	@Provides
	@ViewModelScoped
	fun provideGeoLocationProvider(fusedLocationProviderClient: FusedLocationProviderClient): GeoLocationProvider {
		return GeoLocationProviderImpl(fusedLocationProviderClient)
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
	fun provideGetCityBasedOnCoordsInteractor(locationsService: LocationsService): GetCityBasedOnCoordinatesInteractor {
		return GetCityBasedOnCoordinatesInteractorImpl(ApiConfig.apiKey, locationsService)
	}

	@Provides
	@ViewModelScoped
	fun provideGetDailyForecastInteractor(forecastService: ForecastService): GetDailyForecastInteractor {
		return GetDailyForecastInteractorImpl(ApiConfig.apiKey, forecastService)
	}

	@Provides
	@ViewModelScoped
	fun provideGetCurrentConditionsInteractor(forecastService: ForecastService): GetCurrentConditionsInteractor {
		return GetCurrentConditionsInteractorImpl(ApiConfig.apiKey, forecastService)
	}

	@Provides
	@ViewModelScoped
	fun provideGetWeeklyForecastInteractor(forecastService: ForecastService): GetWeeklyForecastInteractor {
		return GetWeeklyForecastInteractorImpl(ApiConfig.apiKey, forecastService)
	}

	@Provides
	@ViewModelScoped
	fun provideGetTwelveHourForecastInteractor(forecastService: ForecastService): GetTwelveHourForecastInteractor {
		return GetTwelveHourForecastInteractorImpl(ApiConfig.apiKey, forecastService)
	}

	@Provides
	@ViewModelScoped
	fun provideSetSelectedCityLocationKeyInteractor(applicationStorage: ApplicationStorage): SetSelectedCityLocationKeyInteractor {
		return SetSelectedCityLocationKeyInteractorImpl(applicationStorage)
	}

	@Provides
	@ViewModelScoped
	fun provideGetSelectedCityLocationKeyInteractor(applicationStorage: ApplicationStorage): GetSelectedCityLocationKeyInteractor {
		return GetSelectedCityLocationKeyInteractorImpl(applicationStorage)
	}

	@Provides
	@ViewModelScoped
	fun provideToggleUnitsInteractor(applicationStorage: ApplicationStorage): ToggleUnitsInteractor {
		return ToggleUnitsInteractorImpl(applicationStorage)
	}

	@Provides
	@ViewModelScoped
	fun provideGetUnitsInteractor(applicationStorage: ApplicationStorage): GetUnitsInteractor {
		return GetUnitsInteractorImpl(applicationStorage)
	}

	@Provides
	@ViewModelScoped
	fun provideToggleLocationPermissionStateInteractor(applicationStorage: ApplicationStorage): ToggleLocationPermissionStateInteractor {
		return ToggleLocationPermissionStateInteractorImpl(applicationStorage)
	}

	@Provides
	@ViewModelScoped
	fun provideGetLocationPermissionStateInteractor(@ApplicationContext context: Context, applicationStorage: ApplicationStorage): GetLocationPermissionStateInteractor {
		return GetLocationPermissionStateInteractorImpl(context, applicationStorage)
	}
}