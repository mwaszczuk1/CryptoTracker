package pl.mwaszczuk.network.response

import kotlinx.serialization.Serializable

@Serializable
data class DataResponse<out T: Any>(
    val data: T
)
