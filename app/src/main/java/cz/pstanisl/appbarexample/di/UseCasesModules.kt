package cz.pstanisl.appbarexample.di

import cz.pstanisl.appbarexample.model.InboxRepository
import cz.pstanisl.appbarexample.ui.dashboard.GetInboxUseCase
import dagger.Module

@Module
class UseCasesModules {

    fun providesGetInboxUseCase(inboxRepository: InboxRepository): GetInboxUseCase {
        return GetInboxUseCase(inboxRepository)
    }

}