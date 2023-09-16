package khanesh.shared.storage.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import khanesh.shared.core.di.sharedComponentInstance
import khanesh.shared.storage.daos.GenresDao
import org.kodein.di.DI

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {

    @Provides
    @IntoSet
    fun provideStorageModule(): DI.Module = storageModule

    @Provides
    fun provideGenresDao(): GenresDao = sharedComponentInstance()
}
