package injection

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
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
import interactor.forecast.GetCurrentConditionsInteractor
import interactor.forecast.GetDailyForecastInteractor
import interactor.forecast.GetTwelveHourForecastInteractor
import interactor.forecast.GetWeeklyForecastInteractor
import interactor.settings.GetLocationPermissionStateInteractor
import interactor.settings.GetUnitsInteractor
import interactor.settings.ToggleLocationPermissionStateInteractor
import interactor.settings.ToggleUnitsInteractor
import mapper.CityMapper
import mapper.ForecastMapper
import mapper.impl.CityMapperImpl
import mapper.impl.ForecastMapperImpl
import repository.CityRepository
import repository.ForecastRepository
import repository.SettingsRepository
import repository.impl.CityRepositoryImpl
import repository.impl.ForecastRepositoryImpl
import repository.impl.SettingsRepositoryImpl
import usecase.city.AddFavouriteCityUseCase
import usecase.city.impl.AddFavouriteCityUseCaseImpl
import usecase.city.GetFavouriteCitiesUseCase
import usecase.city.GetSelectedCityLocationKeyUseCase
import usecase.city.impl.GetFavouriteCitiesUseCaseImpl
import usecase.city.QueryCitiesUseCase
import usecase.city.impl.QueryCitiesUseCaseImpl
import usecase.city.RemoveFavouriteCityUseCase
import usecase.city.SetSelectedCityLocationKeyUseCase
import usecase.city.impl.RemoveFavouriteCityUseCaseImpl
import usecase.city.ToggleFavouriteCityUseCase
import usecase.city.impl.GetSelectedCityLocationKeyUseCaseImpl
import usecase.city.impl.SetSelectedCityLocationKeyUseCaseImpl
import usecase.city.impl.ToggleFavouriteCityUseCaseImpl
import usecase.forecast.GetCurrentConditionsUseCase
import usecase.forecast.GetDailyForecastUseCase
import usecase.forecast.GetTwelveHourForecastUseCase
import usecase.forecast.GetWeeklyForecastUseCase
import usecase.forecast.impl.GetCurrentConditionsUseCaseImpl
import usecase.forecast.impl.GetDailyForecastUseCaseImpl
import usecase.forecast.impl.GetTwelveHourForecastUseCaseImpl
import usecase.forecast.impl.GetWeeklyForecastUseCaseImpl
import usecase.location.GetCurrentCityUseCase
import usecase.location.impl.GetCurrentCityUseCaseImpl
import usecase.settings.GetLocationPermissionStateUseCase
import usecase.settings.GetUnitsUseCase
import usecase.settings.ToggleLocationPermissionStateUseCase
import usecase.settings.ToggleUnitsUseCase
import usecase.settings.impl.GetLocationPermissionStateUseCaseImpl
import usecase.settings.impl.GetUnitsUseCaseImpl
import usecase.settings.impl.ToggleLocationPermissionStateUseCaseImpl
import usecase.settings.impl.ToggleUnitsUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

	@Provides
	@ViewModelScoped
	fun provideQueryCitiesUseCase(cityRepository: CityRepository): QueryCitiesUseCase {
		return QueryCitiesUseCaseImpl(cityRepository)
	}

	@Provides
	@ViewModelScoped
	fun provideGetFavouriteCitiesUseCase(cityRepository: CityRepository): GetFavouriteCitiesUseCase {
		return GetFavouriteCitiesUseCaseImpl(cityRepository)
	}

	@Provides
	@ViewModelScoped
	fun provideAddFavouriteCityUseCase(cityRepository: CityRepository): AddFavouriteCityUseCase {
		return AddFavouriteCityUseCaseImpl(cityRepository)
	}

	@Provides
	@ViewModelScoped
	fun provideRemoveFavouriteCityUseCase(cityRepository: CityRepository): RemoveFavouriteCityUseCase {
		return RemoveFavouriteCityUseCaseImpl(cityRepository)
	}

	@Provides
	@ViewModelScoped
	fun provideToggleFavouriteCityUseCase(cityRepository: CityRepository): ToggleFavouriteCityUseCase {
		return ToggleFavouriteCityUseCaseImpl(cityRepository)
	}

	@Provides
	@ViewModelScoped
	fun provideGetCurrentCityUseCase(cityRepository: CityRepository): GetCurrentCityUseCase {
		return GetCurrentCityUseCaseImpl(cityRepository)
	}

	@Provides
	@ViewModelScoped
	fun provideGetDailyForecastUseCase(forecastRepository: ForecastRepository): GetDailyForecastUseCase {
		return GetDailyForecastUseCaseImpl(forecastRepository)
	}

	@Provides
	@ViewModelScoped
	fun provideGetCurrentConditionsUseCase(forecastRepository: ForecastRepository): GetCurrentConditionsUseCase {
		return GetCurrentConditionsUseCaseImpl(forecastRepository)
	}

	@Provides
	@ViewModelScoped
	fun provideGetWeeklyForecastUseCase(forecastRepository: ForecastRepository): GetWeeklyForecastUseCase {
		return GetWeeklyForecastUseCaseImpl(forecastRepository)
	}

	@Provides
	@ViewModelScoped
	fun provideGetTwelveHourForecastUseCase(forecastRepository: ForecastRepository): GetTwelveHourForecastUseCase {
		return GetTwelveHourForecastUseCaseImpl(forecastRepository)
	}

	@Provides
	@ViewModelScoped
	fun provideCitiesRepository(
		queryCitiesInteractor: QueryCitiesInteractor,
		getFavouriteCitiesInteractor: GetFavouriteCitiesInteractor,
		addFavouriteCityInteractor: AddFavouriteCityInteractor,
		removeFavouriteCityInteractor: RemoveFavouriteCityInteractor,
		getCurrentGeoLocationInteractor: GetCurrentGeoLocationInteractor,
		getCityBasedOnCoordinatesInteractor: GetCityBasedOnCoordinatesInteractor,
		getSelectedCityLocationKeyInteractor: GetSelectedCityLocationKeyInteractor,
		setSelectedCityLocationKeyInteractor: SetSelectedCityLocationKeyInteractor,
		cityMapper: CityMapper
	): CityRepository {
		return CityRepositoryImpl(
			queryCitiesInteractor,
			getFavouriteCitiesInteractor,
			addFavouriteCityInteractor,
			removeFavouriteCityInteractor,
			getCurrentGeoLocationInteractor,
			getCityBasedOnCoordinatesInteractor,
			getSelectedCityLocationKeyInteractor,
			setSelectedCityLocationKeyInteractor,
			cityMapper
		)
	}

	@Provides
	@ViewModelScoped
	fun provideForecastRepository(
		getDailyForecastInteractor: GetDailyForecastInteractor,
		getCurrentConditionsInteractor: GetCurrentConditionsInteractor,
		getWeeklyForecastInteractor: GetWeeklyForecastInteractor,
		getTwelveHourForecastInteractor: GetTwelveHourForecastInteractor,
		getUnitsInteractor: GetUnitsInteractor,
		forecastMapper: ForecastMapper
	): ForecastRepository {
		return ForecastRepositoryImpl(
			getDailyForecastInteractor,
			getCurrentConditionsInteractor,
			getWeeklyForecastInteractor,
			getTwelveHourForecastInteractor,
			getUnitsInteractor,
			forecastMapper
		)
	}

	@Provides
	@ViewModelScoped
	fun provideSettingsRepository(
		toggleUnitsInteractor: ToggleUnitsInteractor,
		getUnitsInteractor: GetUnitsInteractor,
		toggleLocationPermissionStateInteractor: ToggleLocationPermissionStateInteractor,
		getLocationPermissionStateInteractor: GetLocationPermissionStateInteractor
	): SettingsRepository {
		return SettingsRepositoryImpl(toggleUnitsInteractor, getUnitsInteractor, toggleLocationPermissionStateInteractor, getLocationPermissionStateInteractor)
	}

	@Provides
	@ViewModelScoped
	fun provideCityMapper(): CityMapper {
		return CityMapperImpl()
	}

	@Provides
	@ViewModelScoped
	fun provideForecastMapper(): ForecastMapper {
		return ForecastMapperImpl()
	}

	@Provides
	@ViewModelScoped
	fun provideSetSelectedCityLocationKeyUseCase(cityRepository: CityRepository): SetSelectedCityLocationKeyUseCase {
		return SetSelectedCityLocationKeyUseCaseImpl(cityRepository)
	}

	@Provides
	@ViewModelScoped
	fun provideGetSelectedCityLocationKeyUseCase(cityRepository: CityRepository): GetSelectedCityLocationKeyUseCase {
		return GetSelectedCityLocationKeyUseCaseImpl(cityRepository)
	}

	@Provides
	@ViewModelScoped
	fun provideGetUnitsUseCase(settingsRepository: SettingsRepository): GetUnitsUseCase {
		return GetUnitsUseCaseImpl(settingsRepository)
	}

	@Provides
	@ViewModelScoped
	fun provideToggleUnitsUseCase(settingsRepository: SettingsRepository): ToggleUnitsUseCase {
		return ToggleUnitsUseCaseImpl(settingsRepository)
	}

	@Provides
	@ViewModelScoped
	fun provideToggleLocationPermissionStateUseCase(settingsRepository: SettingsRepository): ToggleLocationPermissionStateUseCase {
		return ToggleLocationPermissionStateUseCaseImpl(settingsRepository)
	}

	@Provides
	@ViewModelScoped
	fun provideGetLocationPermissionStateUseCase(settingsRepository: SettingsRepository): GetLocationPermissionStateUseCase {
		return GetLocationPermissionStateUseCaseImpl(settingsRepository)
	}
}
