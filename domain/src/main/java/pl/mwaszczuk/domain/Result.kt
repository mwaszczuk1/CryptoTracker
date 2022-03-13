package pl.mwaszczuk.domain

sealed class Result<out T: Any> {
    object Empty : Result<Nothing>()
    class Success<out T: Any>(val data: T) : Result<T>()
    class Error(val error: Throwable) : Result<Nothing>()
}
