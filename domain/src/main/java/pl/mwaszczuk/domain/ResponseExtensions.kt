package pl.mwaszczuk.domain

import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber

inline fun <T : Any> safeApiCall(
    block: () -> Result<T>
): Result<T> = try {
    block()
} catch (e: Throwable) {
    Result.Error(e)
}

inline fun <T : Any, O : Any> Response<T>.unwrap(
    mappingBlock: (T) -> O
): Result<O> {
    return when {
        isSuccessful -> if (body() != null) {
            Result.Success(
                mappingBlock(body() as T)
            )
        } else {
            Result.Empty
        }
        else -> Result.Error(HttpException(this))
    }
}

fun <T : Any, O : Any> Result<T>.mapAsViewState(
    mappingBlock: (T) -> O
): ViewState<O> =
    try {
        when (this) {
            is Result.Success -> ViewState.Success(mappingBlock(data))
            is Result.Error -> {
                Timber.e(error)
                ViewState.Error(error)
            }
            is Result.Empty -> ViewState.Empty
        }
    } catch (e: Throwable) {
        Timber.e(e)
        ViewState.Error(e)
    }
