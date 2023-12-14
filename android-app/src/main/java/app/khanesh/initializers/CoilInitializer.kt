package app.khanesh.initializers

import android.content.Context
import coil.Coil
import coil.ImageLoader
import coil.request.CachePolicy
import dagger.hilt.android.qualifiers.ApplicationContext
import khanesh.core.base.initializer.Initializer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoilInitializer @Inject constructor(
    @ApplicationContext private val context: Context,
) : Initializer {

    override fun init() {
        Coil.setImageLoader {
            ImageLoader.Builder(context)
                .diskCachePolicy(CachePolicy.ENABLED)
                .crossfade(true)
                .build()
        }
    }
}
