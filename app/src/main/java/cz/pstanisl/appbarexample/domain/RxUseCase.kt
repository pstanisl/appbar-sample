package cz.pstanisl.appbarexample.domain

import io.reactivex.Observable
import io.reactivex.Single

abstract class RxUseCase<in Q : RxUseCase.RequestValues, P : RxUseCase.ResponseValues> {

    /**
     * Builds an [Single] which will be used when executing the current [RxUseCase].
     */
    protected abstract fun buildUseCase(requestValues: Q): Observable<P>

    /**
     * Data passed to a request.
     */
    interface RequestValues
    /**
     * Data received from a response.
     */
    interface ResponseValues

}