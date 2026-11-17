package cl.bootcamp.leeconnos

import android.app.Application
import cl.bootcamp.leeconnos.di.appModule
import com.google.android.libraries.places.api.Places
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class LeeconnosApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@LeeconnosApplication)
            modules(appModule)
        }

        if (!Places.isInitialized()) {
            Places.initializeWithNewPlacesApiEnabled(applicationContext, BuildConfig.MAPS_API_KEY)
        }
    }
}