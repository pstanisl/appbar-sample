package cz.pstanisl.appbarexample.ui.dashboard

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import cz.pstanisl.appbarexample.model.Message
import io.reactivex.observers.DisposableObserver
import timber.log.Timber
import javax.inject.Inject

class DashboardViewModel @Inject constructor(
        private val getInboxUseCase: GetInboxUseCase
) : ViewModel() {

    fun getInbox() {
        getInboxUseCase.execute(
                InboxObserver() as DisposableObserver<GetInboxUseCase.ResponseValues>,
                GetInboxUseCase.RequestValues()
                )
    }

    inner class InboxObserver : DisposableObserver<List<Message>>() {
        override fun onComplete() {
            Timber.d("Get inbox messages completed.")
        }

        override fun onNext(t: List<Message>) {
            Timber.d("Inbox messages: %s", t)
        }

        override fun onError(e: Throwable) {
            Timber.e(e)
        }
    }

}