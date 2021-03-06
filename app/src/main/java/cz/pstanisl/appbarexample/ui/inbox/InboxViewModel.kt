package cz.pstanisl.appbarexample.ui.inbox

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cz.pstanisl.appbarexample.model.Message
import cz.pstanisl.appbarexample.vo.Resource
import io.reactivex.observers.DisposableObserver
import timber.log.Timber
import javax.inject.Inject

class InboxViewModel @Inject constructor(
        private val getInboxUseCase: GetInboxUseCase
) : ViewModel() {

    val resourceLiveData = MutableLiveData<Resource<List<Message>>>()

    fun getInbox(reload: Boolean) {
        // Dispose previous cases
//        getInboxUseCase.dispose()
        resourceLiveData.value = Resource.loading()
        // Initiate new use case
        val observer: DisposableObserver<GetInboxUseCase.ResponseValues> = InboxObserver()
        getInboxUseCase.execute(observer, GetInboxUseCase.RequestValues(reload))
    }

    inner class InboxObserver : DisposableObserver<GetInboxUseCase.ResponseValues>() {
        override fun onComplete() {
            Timber.d("Get inbox messages completed.")
        }

        override fun onNext(response: GetInboxUseCase.ResponseValues) {
            resourceLiveData.value = Resource.success(response.messages)
        }

        override fun onError(e: Throwable) {
            resourceLiveData.value = Resource.failure(e)
        }
    }

}