package injection

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import interactor.QueryCitiesInteractor
import interactor.QueryCitiesInteractorImpl

@Module
@InstallIn(ViewModelComponent::class)
class DataModule {

    @Provides
    @ViewModelScoped
    fun provideQueryCitiesInteractor(): QueryCitiesInteractor {
        return QueryCitiesInteractorImpl()
    }
}