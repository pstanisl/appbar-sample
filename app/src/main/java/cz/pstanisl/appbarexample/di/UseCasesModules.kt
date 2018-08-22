package cz.pstanisl.appbarexample.di

import cz.pstanisl.appbarexample.model.InboxRepository
import cz.pstanisl.appbarexample.ui.detail.GetMessageUseCase
import cz.pstanisl.appbarexample.ui.inbox.GetInboxUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCasesModules {

    @Provides
    fun providesGetInboxUseCase(inboxRepository: InboxRepository): GetInboxUseCase {
        return GetInboxUseCase(inboxRepository)
    }

    @Provides
    fun providesGetMessageUseCase(inboxRepository: InboxRepository): GetMessageUseCase {
        return GetMessageUseCase(inboxRepository)
    }

}