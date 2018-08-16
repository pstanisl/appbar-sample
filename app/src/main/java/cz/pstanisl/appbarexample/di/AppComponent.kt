package cz.pstanisl.appbarexample.di

import android.app.Application
import cz.pstanisl.appbarexample.AppBarExampleApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton
import dagger.android.AndroidInjector



@Singleton
@Component(modules = [
    ActivityModule::class,
    AndroidInjectionModule::class,
    NetModule::class,
    UseCasesModules::class,
    ViewModelFactoryModule::class,
    ViewModelModule::class
])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance fun application(application: Application) : Builder

        fun build() : AppComponent
    }

    fun inject(appBarExampleApp: AppBarExampleApp)

}