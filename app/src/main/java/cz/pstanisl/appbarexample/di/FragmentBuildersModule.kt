package cz.pstanisl.appbarexample.di

import cz.pstanisl.appbarexample.ui.dashboard.DashboardFragment
import dagger.android.ContributesAndroidInjector

abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeDashboardFragment(): DashboardFragment

}