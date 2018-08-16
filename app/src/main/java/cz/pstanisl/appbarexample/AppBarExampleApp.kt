package cz.pstanisl.appbarexample

import android.app.Application
import cz.pstanisl.appbarexample.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class AppBarExampleApp: DaggerApplication()  {

    override fun applicationInjector(): AndroidInjector<out AppBarExampleApp> {
        return DaggerAppComponent.builder().create(this)
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}