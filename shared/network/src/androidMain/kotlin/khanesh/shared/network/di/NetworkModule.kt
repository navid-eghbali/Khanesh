package khanesh.shared.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import khanesh.shared.base.di.sharedComponentInstance
import khanesh.shared.network.NetworkClient
import org.kodein.di.DI

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @IntoSet
    fun provideNetworkModule(): DI.Module = networkModule

    @Provides
    fun provideNetworkClient(): NetworkClient = sharedComponentInstance()
}
