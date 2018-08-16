package cz.pstanisl.appbarexample.api

import cz.pstanisl.appbarexample.model.Message
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * REST API access points
 */
interface AndroidHiveApi {

    @GET("inbox.json")
    fun getInbox() : Observable<List<Message>>

}

