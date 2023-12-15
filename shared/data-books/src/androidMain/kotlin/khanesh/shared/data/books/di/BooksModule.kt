package khanesh.shared.data.books.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import org.kodein.di.DI

@Module
@InstallIn(SingletonComponent::class)
object BooksModule {

    @Provides
    @IntoSet
    fun provideBooksModule(): DI.Module = booksModule
}
