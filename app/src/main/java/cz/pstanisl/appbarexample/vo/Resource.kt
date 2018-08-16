package cz.pstanisl.appbarexample.vo

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Resource<out T> {

    class Loading<out T>: Resource<T>()
    data class Failure<out T>(val e: Throwable): Resource<T>() {
        override fun toString(): String {
            return "Resource.Failure{e=$e}"
        }
    }
    data class Success<out T>(val data: T?): Resource<T>() {
        override fun toString(): String {
            return "Resource.Success{data=$data}"
        }
    }

    companion object {

        fun <T> success(data: T?): Resource<T> = Success(data)

        fun <T> failure(e: Throwable): Resource<T> = Failure(e)

        fun <T> loading(): Resource<T> = Loading()

    }

}