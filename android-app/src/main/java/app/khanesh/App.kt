package app.khanesh

import android.app.Application
import app.khanesh.initializers.AppInitializer
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var initializers: AppInitializer

    override fun onCreate() {
        super.onCreate()
        initializers.init()
    }
}
