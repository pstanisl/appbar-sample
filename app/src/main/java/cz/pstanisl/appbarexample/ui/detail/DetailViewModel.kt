package cz.pstanisl.appbarexample.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cz.pstanisl.appbarexample.model.Message
import cz.pstanisl.appbarexample.vo.Resource
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class DetailViewModel @Inject constructor(
        private val getMessageUseCase: GetMessageUseCase,
        private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    val resourceLiveData = MutableLiveData<Resource<Message>>()

    fun getMessage(id: Int) {
        resourceLiveData.value = Resource.loading()

        val observer: DisposableObserver<GetMessageUseCase.ResponseValues> = MessageObserver()
        getMessageUseCase.execute(observer, GetMessageUseCase.RequestValues(id))
    }

    fun toggleFavorite(id: Int) {
        resourceLiveData.value = Resource.loading()

        val observer: DisposableObserver<GetMessageUseCase.ResponseValues> = MessageObserver()
        toggleFavoriteUseCase.execute(observer, GetMessageUseCase.RequestValues(id))
    }

    inner class MessageObserver : DisposableObserver<GetMessageUseCase.ResponseValues>() {

        override fun onComplete() { }

        override fun onNext(response: GetMessageUseCase.ResponseValues) {
            resourceLiveData.value = Resource.success(response.message)
        }

        override fun onError(e: Throwable) {
            resourceLiveData.value = Resource.failure(e)
        }

    }

}