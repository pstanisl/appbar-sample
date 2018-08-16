package cz.pstanisl.appbarexample.di

import cz.pstanisl.appbarexample.AppBarExampleApp
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
interface AppComponent: AndroidInjector<AppBarExampleApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<AppBarExampleApp>()

}