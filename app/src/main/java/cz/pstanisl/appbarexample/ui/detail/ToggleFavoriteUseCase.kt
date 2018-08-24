package cz.pstanisl.appbarexample.ui.detail

import cz.pstanisl.appbarexample.domain.SimpleUseCase
import cz.pstanisl.appbarexample.model.InboxRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(private val inboxRepository: InboxRepository) : SimpleUseCase<GetMessageUseCase.RequestValues, GetMessageUseCase.ResponseValues>(Schedulers.io(), AndroidSchedulers.mainThread()) {

    override fun buildUseCase(requestValues: GetMessageUseCase.RequestValues): Observable<GetMessageUseCase.ResponseValues> {
        return inboxRepository.toggleFavorite(requestValues.id).andThen(inboxRepository.getMessage(requestValues.id)).map(GetMessageUseCase::ResponseValues)
    }
}