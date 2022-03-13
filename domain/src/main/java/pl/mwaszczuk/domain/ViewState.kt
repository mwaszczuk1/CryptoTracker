package pl.mwaszczuk.domain

sealed class ViewState<out T: Any> {

    object Loading : ViewState<Nothing>()
    object Empty : ViewState<Nothing>()
    class Error(val error: Throwable) : ViewState<Nothing>()
    class Success<out T: Any>(val data: T) : ViewState<T>()
}
