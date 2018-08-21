package cz.pstanisl.appbarexample.di

import cz.pstanisl.appbarexample.ui.inbox.InboxFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeDashboardFragment(): InboxFragment

}