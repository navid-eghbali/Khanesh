package app.khanesh.initializers

import khanesh.core.base.initializer.Initializer
import khanesh.shared.core.di.sharedComponent
import org.kodein.di.DI
import javax.inject.Inject

class SharedComponentsInitializer @Inject constructor(
    private val sharedModules: Set<DI.Module>
) : Initializer {

    override fun init() {
        sharedComponent.addConfig {
            sharedModules.forEach { import(it) }
        }
    }
}
