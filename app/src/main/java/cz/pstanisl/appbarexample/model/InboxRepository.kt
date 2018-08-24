package cz.pstanisl.appbarexample.model

import cz.pstanisl.appbarexample.api.AndroidHiveApi
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InboxRepository @Inject constructor(private val androidHiveApi: AndroidHiveApi) {

    private var cache: List<Message> = emptyList()

    fun getInbox(reload: Boolean): Observable<List<Message>> {
        // Clear cache and initiate reloading
        if (reload) {
            cache = emptyList()
        }
        // Return loaded list or data from cache
        return if (cache.isEmpty()) {
            androidHiveApi.getInbox().doAfterNext { cache = it }
        } else {
            Observable.just(cache)
        }
    }

    fun getMessage(id: Int): Observable<Message> {
        return getInbox(false).map { messages ->
            messages.first { it.id == id }
        }
    }

    fun toggleFavorite(id: Int): Completable {
        return Completable.fromObservable(getInbox(false).map { messages ->
            messages.first { it.id == id }
        }.map { message ->
            message.isImportant = message.isImportant.not()
        })
    }
}