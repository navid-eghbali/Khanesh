package khanesh.shared.core.di

import org.kodein.di.conf.ConfigurableDI
import org.kodein.di.direct
import org.kodein.di.instance

val sharedComponent: ConfigurableDI = ConfigurableDI()

inline fun <reified T> sharedComponentInstance(): T = sharedComponent.direct.instance()
