package injection

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import interactor.QueryCitiesInteractor
import repository.cities.CitiesRepository
import repository.cities.CitiesRepositoryImpl
import usecase.cities.QueryCitiesUseCase
import usecase.cities.QueryCitiesUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    @ViewModelScoped
    fun provideQueryCitiesUseCase(citiesRepository: CitiesRepository) : QueryCitiesUseCase {
        return QueryCitiesUseCaseImpl(citiesRepository = citiesRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideCitiesRepository(queryCitiesInteractor: QueryCitiesInteractor): CitiesRepository {
        return CitiesRepositoryImpl(queryCitiesInteractor = queryCitiesInteractor)
    }
}
