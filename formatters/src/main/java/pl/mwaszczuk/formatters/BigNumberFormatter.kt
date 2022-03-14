package pl.mwaszczuk.formatters

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.*
import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.pow

class BigNumberFormatter {
    fun format(number: Double): String {
        val decimalSymbols = DecimalFormatSymbols.getInstance()
        decimalSymbols.decimalSeparator = '.'
        val suffix = charArrayOf(' ', 'k', 'M', 'B', 'T', 'P', 'E')
        val numValue = number.toLong()
        val value = floor(log10(numValue.toDouble())).toInt()
        val base = value / 3
        return if (value >= 3 && base < suffix.size) {
            DecimalFormat("#0.0", decimalSymbols).format(
                numValue / 10.0.pow((base * 3).toDouble())
            ) + suffix[base]
        } else {
            DecimalFormat("#,##0", decimalSymbols).format(numValue)
        }
    }
}
