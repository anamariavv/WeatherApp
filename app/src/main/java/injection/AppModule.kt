package injection

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import navigation.NavigationDelegate
import navigation.NavigationDelegateImpl
import navigation.Router
import navigation.RouterImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRouter(navigationDelegate: NavigationDelegate): Router = RouterImpl(navigationDelegate)

    @Provides
    @Singleton
    fun provideNavigationDelegate(): NavigationDelegate = NavigationDelegateImpl()

}