package pl.mwaszczuk.domain.model

import androidx.annotation.StringRes

data class Currency(
    @StringRes val currencyStringRes: Int,
    val amount: Double
)
