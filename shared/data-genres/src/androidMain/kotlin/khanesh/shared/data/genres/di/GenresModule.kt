package khanesh.shared.data.genres.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import khanesh.shared.base.di.sharedComponentInstance
import khanesh.shared.data.genres.GenresRepository
import org.kodein.di.DI

@Module
@InstallIn(SingletonComponent::class)
object GenresModule {

    @Provides
    @IntoSet
    fun provideGenresModule(): DI.Module = genresModule

    @Provides
    fun provideGenresRepository(): GenresRepository = sharedComponentInstance()
}
