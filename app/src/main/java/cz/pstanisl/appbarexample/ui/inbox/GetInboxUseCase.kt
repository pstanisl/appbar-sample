package cz.pstanisl.appbarexample.ui.inbox

import cz.pstanisl.appbarexample.domain.RxUseCase
import cz.pstanisl.appbarexample.domain.SimpleUseCase
import cz.pstanisl.appbarexample.model.InboxRepository
import cz.pstanisl.appbarexample.model.Message
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetInboxUseCase @Inject constructor(private val inboxRepository: InboxRepository) : SimpleUseCase<GetInboxUseCase.RequestValues, GetInboxUseCase.ResponseValues>(Schedulers.io(), AndroidSchedulers.mainThread()) {

    override fun buildUseCase(requestValues: RequestValues): Observable<ResponseValues> {
        return inboxRepository.getInbox(requestValues.reload).map(::ResponseValues)
    }

    data class RequestValues constructor(val reload: Boolean) : RxUseCase.RequestValues

    data class ResponseValues constructor(val messages: List<Message>) : RxUseCase.ResponseValues
}
