package cz.pstanisl.appbarexample

import android.app.Activity
import android.app.Application
import cz.pstanisl.appbarexample.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class AppBarExampleApp: Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

//    override fun applicationInjector(): AndroidInjector<out AppBarExampleApp> {
//        return DaggerAppComponent.builder().create(this)
//    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        AppInjector.init(this)
    }

}