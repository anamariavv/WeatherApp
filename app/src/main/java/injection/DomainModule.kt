package injection

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import interactor.AddFavouriteCityInteractor
import interactor.GetFavouriteCitiesInteractor
import interactor.QueryCitiesInteractor
import interactor.RemoveFavouriteCityInteractor
import mapper.CityMapper
import mapper.CityMapperImpl
import repository.cities.CitiesRepository
import repository.cities.CitiesRepositoryImpl
import usecase.cities.AddFavouriteCityUseCase
import usecase.cities.AddFavouriteCityUseCaseImpl
import usecase.cities.GetFavouriteCitiesUseCase
import usecase.cities.GetFavouriteCitiesUseCaseImpl
import usecase.cities.QueryCitiesUseCase
import usecase.cities.QueryCitiesUseCaseImpl
import usecase.cities.RemoveFavouriteCityUseCase
import usecase.cities.RemoveFavouriteCityUseCaseImpl

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
    fun provideAddFavouriteCityUseCase(citiesRepository: CitiesRepository): AddFavouriteCityUseCase {
        return AddFavouriteCityUseCaseImpl(citiesRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideRemoveFavouriteCityUseCase(citiesRepository: CitiesRepository): RemoveFavouriteCityUseCase {
        return RemoveFavouriteCityUseCaseImpl(citiesRepository)
    }


    @Provides
    @ViewModelScoped
    fun provideCitiesRepository(
        queryCitiesInteractor: QueryCitiesInteractor,
        getFavouriteCitiesInteractor: GetFavouriteCitiesInteractor,
        addFavouriteCityInteractor: AddFavouriteCityInteractor,
        removeFavouriteCityInteractor: RemoveFavouriteCityInteractor,
        cityMapper: CityMapper
    ): CitiesRepository {
        return CitiesRepositoryImpl(
            queryCitiesInteractor,
            getFavouriteCitiesInteractor,
            addFavouriteCityInteractor,
            removeFavouriteCityInteractor,
            cityMapper
        )
    }

    @Provides
    @ViewModelScoped
    fun provideCityMapper(): CityMapper {
        return CityMapperImpl()
    }
}
