package app.khanesh.initializers

import dagger.Lazy
import khanesh.core.base.initializer.Initializer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppInitializer @Inject constructor(
    private val initializers: Lazy<Set<@JvmSuppressWildcards Initializer>>
) {

    fun init() {
        initializers.get()
            .forEach { it.init() }
    }
}
