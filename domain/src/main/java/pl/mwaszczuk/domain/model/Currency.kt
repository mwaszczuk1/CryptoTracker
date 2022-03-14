package pl.mwaszczuk.domain.model

import androidx.annotation.StringRes
import java.math.BigDecimal

data class Currency(
    @StringRes val currencyStringRes: Int,
    val amount: BigDecimal
)
