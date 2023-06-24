package injection

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import interactor.GetFavouriteCitiesInteractor
import interactor.QueryCitiesInteractor
import mapper.CityMapper
import mapper.CityMapperImpl
import repository.cities.CitiesRepository
import repository.cities.CitiesRepositoryImpl
import usecase.cities.GetFavouriteCitiesUseCase
import usecase.cities.GetFavouriteCitiesUseCaseImpl
import usecase.cities.QueryCitiesUseCase
import usecase.cities.QueryCitiesUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    @ViewModelScoped
    fun provideQueryCitiesUseCase(citiesRepository: CitiesRepository): QueryCitiesUseCase {
        return QueryCitiesUseCaseImpl(citiesRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetFavouriteCitiesUseCase(citiesRepository: CitiesRepository): GetFavouriteCitiesUseCase {
        return GetFavouriteCitiesUseCaseImpl(citiesRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideCitiesRepository(
        queryCitiesInteractor: QueryCitiesInteractor,
        getFavouriteCitiesInteractor: GetFavouriteCitiesInteractor,
        cityMapper: CityMapper
    ): CitiesRepository {
        return CitiesRepositoryImpl(queryCitiesInteractor, getFavouriteCitiesInteractor, cityMapper)
    }

    @Provides
    @ViewModelScoped
    fun provideCityMapper(): CityMapper {
        return CityMapperImpl()
    }
}
