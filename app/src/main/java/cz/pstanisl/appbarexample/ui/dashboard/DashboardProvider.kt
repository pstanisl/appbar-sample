package cz.pstanisl.appbarexample.ui.dashboard

import cz.pstanisl.appbarexample.model.InboxRepository
import dagger.Module
import dagger.Provides
import cz.pstanisl.appbarexample.ui.DetailFragment
import dagger.android.ContributesAndroidInjector

//@Module
//class DashboardProvider {
//
//
////    fun provideDashboardFragment()
//
//    @Provides
//    fun provideGetInboxUseCase(inboxRepository: InboxRepository): GetInboxUseCase {
//        return GetInboxUseCase(inboxRepository)
//    }
//
//}