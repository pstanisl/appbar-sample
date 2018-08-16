package cz.pstanisl.appbarexample.model

import cz.pstanisl.appbarexample.api.AndroidHiveApi
import io.reactivex.Observable
import javax.inject.Inject

class InboxRepository @Inject constructor(private val androidHiveApi: AndroidHiveApi) {

    fun getInbox(): Observable<List<Message>> = androidHiveApi.getInbox()

}