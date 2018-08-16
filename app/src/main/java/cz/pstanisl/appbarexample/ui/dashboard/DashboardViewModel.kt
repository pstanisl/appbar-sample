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
        val observer: DisposableObserver<GetInboxUseCase.ResponseValues> = InboxObserver()
        getInboxUseCase.execute(observer, GetInboxUseCase.RequestValues())
    }

    inner class InboxObserver : DisposableObserver<GetInboxUseCase.ResponseValues>() {
        override fun onComplete() {
            Timber.d("Get inbox messages completed.")
        }

        override fun onNext(t: GetInboxUseCase.ResponseValues) {
            Timber.d("Inbox messages: %s", t)
        }

        override fun onError(e: Throwable) {
            Timber.e(e)
        }
    }

}