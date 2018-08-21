package cz.pstanisl.appbarexample.domain

import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver


abstract class SimpleUseCase<in Q : RxUseCase.RequestValues, P : RxUseCase.ResponseValues>(
        private val subscribeOn: Scheduler, private val observeOn: Scheduler): RxUseCase<Q, P>() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

    fun execute(observer: DisposableObserver<P>, requestValues: Q) {
        val observable = buildUseCase(requestValues)
                .subscribeOn(subscribeOn)
                .observeOn(observeOn)
        addDisposable(observable.subscribeWith(observer))
    }


    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}