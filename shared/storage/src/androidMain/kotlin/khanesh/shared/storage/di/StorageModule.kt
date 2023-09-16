package khanesh.shared.storage.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import org.kodein.di.DI

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {

    @Provides
    @IntoSet
    fun provideStorageModule(): DI.Module = storageModule
}
