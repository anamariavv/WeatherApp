package injection

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import interactor.QueryCitiesInteractor
import interactor.QueryCitiesInteractorImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import source.network.LocationsService

@Module
@InstallIn(ViewModelComponent::class)
class DataModule {

    @Provides
    @ViewModelScoped
    fun provideQueryCitiesInteractor(locationsService: LocationsService): QueryCitiesInteractor {
        return QueryCitiesInteractorImpl(locationsService)
    }

    @Provides
    @ViewModelScoped
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @ViewModelScoped
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder().baseUrl("http://dataservice.accuweather.com/")
            .addConverterFactory(gsonConverterFactory).build()
    }

    @Provides
    @ViewModelScoped
    fun provideLocationService(retrofit: Retrofit): LocationsService {
        return retrofit.create(LocationsService::class.java)
    }
}