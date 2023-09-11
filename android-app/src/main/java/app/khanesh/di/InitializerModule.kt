package app.khanesh.di

import app.khanesh.initializers.SharedComponentsInitializer
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import khanesh.core.base.initializer.Initializer

@Module
@InstallIn(SingletonComponent::class)
abstract class InitializerModule {

    @Binds
    @IntoSet
    abstract fun bindSharedComponentsInitializer(impl: SharedComponentsInitializer): Initializer
}
