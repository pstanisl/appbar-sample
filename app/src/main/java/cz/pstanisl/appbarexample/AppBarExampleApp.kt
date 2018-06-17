package cz.pstanisl.appbarexample

import android.app.Application
import timber.log.Timber

class AppBarExampleApp: Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}