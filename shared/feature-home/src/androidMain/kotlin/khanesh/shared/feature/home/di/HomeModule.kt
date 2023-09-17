package khanesh.shared.feature.home.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import khanesh.shared.base.di.sharedComponentInstance
import khanesh.shared.feature.home.repository.HomeRepository
import org.kodein.di.DI

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    @IntoSet
    fun provideHomeModule(): DI.Module = homeModule

    @Provides
    fun provideHomeRepository(): HomeRepository = sharedComponentInstance()
}
