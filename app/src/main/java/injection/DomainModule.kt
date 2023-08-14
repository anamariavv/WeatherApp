package injection

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import interactor.*
import mapper.CityMapper
import mapper.ForecastMapper
import mapper.impl.CityMapperImpl
import mapper.impl.ForecastMapperImpl
import repository.CityRepository
import repository.ForecastRepository
import repository.impl.CityRepositoryImpl
import repository.impl.ForecastRepositoryImpl
import usecase.city.AddFavouriteCityUseCase
import usecase.city.impl.AddFavouriteCityUseCaseImpl
import usecase.city.GetFavouriteCitiesUseCase
import usecase.city.impl.GetFavouriteCitiesUseCaseImpl
import usecase.city.QueryCitiesUseCase
import usecase.city.impl.QueryCitiesUseCaseImpl
import usecase.city.RemoveFavouriteCityUseCase
import usecase.city.impl.RemoveFavouriteCityUseCaseImpl
import usecase.city.ToggleFavouriteCityUseCase
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
		cityMapper: CityMapper
	): CityRepository {
		return CityRepositoryImpl(
				queryCitiesInteractor,
				getFavouriteCitiesInteractor,
				addFavouriteCityInteractor,
				removeFavouriteCityInteractor,
				getCurrentGeoLocationInteractor,
				getCityBasedOnCoordinatesInteractor,
				cityMapper
		)
	}

	@Provides
	fun provideForecastRepository(
		getDailyForecastInteractor: GetDailyForecastInteractor,
		getCurrentConditionsInteractor: GetCurrentConditionsInteractor,
		getWeeklyForecastInteractor: GetWeeklyForecastInteractor,
		getTwelveHourForecastInteractor: GetTwelveHourForecastInteractor,
		forecastMapper: ForecastMapper
	): ForecastRepository {
		return ForecastRepositoryImpl(getDailyForecastInteractor, getCurrentConditionsInteractor, getWeeklyForecastInteractor, getTwelveHourForecastInteractor, forecastMapper)
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
}
