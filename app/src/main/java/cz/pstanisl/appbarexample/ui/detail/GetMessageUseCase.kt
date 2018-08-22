package cz.pstanisl.appbarexample.ui.detail

import cz.pstanisl.appbarexample.domain.RxUseCase
import cz.pstanisl.appbarexample.domain.SimpleUseCase
import cz.pstanisl.appbarexample.model.InboxRepository
import cz.pstanisl.appbarexample.model.Message
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetMessageUseCase @Inject constructor(private val inboxRepository: InboxRepository) : SimpleUseCase<GetMessageUseCase.RequestValues, GetMessageUseCase.ResponseValues>(Schedulers.io(), AndroidSchedulers.mainThread()) {

    override fun buildUseCase(requestValues: RequestValues): Observable<ResponseValues> {
        return inboxRepository.getMessage(requestValues.id).map(::ResponseValues)
    }

    data class RequestValues constructor(val id: Int) : RxUseCase.RequestValues

    data class ResponseValues constructor(val message: Message) : RxUseCase.ResponseValues
}