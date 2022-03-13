package pl.mwaszczuk.domain

import retrofit2.HttpException
import retrofit2.Response

inline fun <T : Any, O : Any> Response<T>.unwrap(
    mappingBlock: (T) -> O
): Result<O> {
    return try {
        when {
            isSuccessful -> if (body() != null) {
                Result.Success(
                    mappingBlock(body() as T)
                )
            } else {
                Result.Empty
            }
            else -> Result.Error(HttpException(this))
        }
    } catch (e: Throwable) {
        Result.Error(e)
    }
}
