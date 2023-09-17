package app.khanesh.initializers

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import khanesh.core.base.initializer.Initializer
import khanesh.shared.base.di.sharedComponent
import khanesh.shared.storage.DriverFactory
import org.kodein.di.DI
import org.kodein.di.bindProvider
import javax.inject.Inject

class SharedComponentsInitializer @Inject constructor(
    @ApplicationContext private val context: Context,
    private val sharedModules: Set<DI.Module>
) : Initializer {

    override fun init() {
        sharedComponent.addConfig {
            bindProvider { DriverFactory(context).createDriver() }
            sharedModules.forEach { import(it) }
        }
    }
}
