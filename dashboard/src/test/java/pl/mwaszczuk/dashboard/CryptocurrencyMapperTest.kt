package pl.mwaszczuk.dashboard

import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import pl.mwaszczuk.dashboard.model.Cryptocurrency
import pl.mwaszczuk.dashboard.model.CryptocurrencyMapper
import pl.mwaszczuk.dashboard.model.Trend
import pl.mwaszczuk.domain.model.CryptocurrencyData
import pl.mwaszczuk.domain.model.Currency
import pl.mwaszczuk.domain.model.PercentChange
import pl.mwaszczuk.formatters.BigNumberFormatter

@Suppress("MaxLineLength")
class CryptocurrencyMapperTest {

    private val formattedNumber = "1.0B"

    private val numberFormatter: BigNumberFormatter = mock {
        whenever(it.format(any())) doReturn formattedNumber
    }
    private val mapper = CryptocurrencyMapper(numberFormatter)

    private val input = listOf(
        CryptocurrencyData("1", "Bitcoin", "BTC", "", 100.0,  3.0, 1.0, 1.0),
        CryptocurrencyData("2", "Ethereum", "ETH", "", 100.0,  5.0, 1.0, 1.0),
        CryptocurrencyData("3", "Solana", "SOL", "", 100.0,  2.0, -1.0, -1.0),
        CryptocurrencyData("4", "Terra", "LUNA", "", 100.0,  4.0, 1.0, 1.0),
        CryptocurrencyData("5", "Binance Coin", "BNB", "", 100.0,  1.0, 1.0, 1.0)

    )

    private val wantedOutput = listOf(
        Cryptocurrency("1", "Bitcoin", "BTC", "", Currency(R.string.currency_dollar_value, 100.0.toBigDecimal()),  formattedNumber, PercentChange("1.0", true), PercentChange("1.0", true), Trend.Flat),
        Cryptocurrency("2", "Ethereum", "ETH", "", Currency(R.string.currency_dollar_value, 100.0.toBigDecimal()),  formattedNumber, PercentChange("1.0", true), PercentChange("1.0", true), Trend.Flat),
        Cryptocurrency("3", "Solana", "SOL", "", Currency(R.string.currency_dollar_value, 100.0.toBigDecimal()),  formattedNumber, PercentChange("-1.0", false), PercentChange("-1.0", false), Trend.Flat),
        Cryptocurrency("4", "Terra", "LUNA", "", Currency(R.string.currency_dollar_value, 100.0.toBigDecimal()),  formattedNumber, PercentChange("1.0", true), PercentChange("1.0", true), Trend.Flat),
        Cryptocurrency("5", "Binance Coin", "BNB", "", Currency(R.string.currency_dollar_value, 100.0.toBigDecimal()),  formattedNumber, PercentChange("1.0", true), PercentChange("1.0", true), Trend.Flat)
    )

    private val previousData = listOf(
        Cryptocurrency("1", "Bitcoin", "BTC", "", Currency(R.string.currency_dollar_value, 99.9.toBigDecimal()),  formattedNumber, PercentChange("1.0", true), PercentChange("1.0", true), Trend.Flat),
        Cryptocurrency("2", "Ethereum", "ETH", "", Currency(R.string.currency_dollar_value, 101.0.toBigDecimal()),  formattedNumber, PercentChange("1.0", true), PercentChange("1.0", true), Trend.Flat),
        Cryptocurrency("3", "Solana", "SOL", "", Currency(R.string.currency_dollar_value, 15.0.toBigDecimal()),  formattedNumber, PercentChange("-1.0", false), PercentChange("-1.0", false), Trend.Flat),
        Cryptocurrency("4", "Terra", "LUNA", "", Currency(R.string.currency_dollar_value, 213.1.toBigDecimal()),  formattedNumber, PercentChange("1.0", true), PercentChange("1.0", true), Trend.Flat),
        Cryptocurrency("5", "Binance Coin", "BNB", "", Currency(R.string.currency_dollar_value, 100.0.toBigDecimal()),  formattedNumber, PercentChange("1.0", true), PercentChange("1.0", true), Trend.Flat)
    )

    private val wantedOutputWithPreviousData = listOf(
        Cryptocurrency("1", "Bitcoin", "BTC", "", Currency(R.string.currency_dollar_value, 100.0.toBigDecimal()),  formattedNumber, PercentChange("1.0", true), PercentChange("1.0", true), Trend.Up),
        Cryptocurrency("2", "Ethereum", "ETH", "", Currency(R.string.currency_dollar_value, 100.0.toBigDecimal()), formattedNumber, PercentChange("1.0", true), PercentChange("1.0", true), Trend.Down),
        Cryptocurrency("3", "Solana", "SOL", "", Currency(R.string.currency_dollar_value, 100.0.toBigDecimal()),  formattedNumber, PercentChange("-1.0", false), PercentChange("-1.0", false), Trend.Up),
        Cryptocurrency("4", "Terra", "LUNA", "", Currency(R.string.currency_dollar_value, 100.0.toBigDecimal()),  formattedNumber, PercentChange("1.0", true), PercentChange("1.0", true), Trend.Down),
        Cryptocurrency("5", "Binance Coin", "BNB", "", Currency(R.string.currency_dollar_value, 100.0.toBigDecimal()),  formattedNumber, PercentChange("1.0", true), PercentChange("1.0", true), Trend.Flat)
    )

    @Test
    fun `test proper initial mapping`() {
        val result = mapper.map(input, null)
        Assert.assertEquals(wantedOutput, result)
    }

    @Test
    fun `test proper mapping with previous data present`() {
        val result = mapper.map(input, previousData)
        Assert.assertEquals(wantedOutputWithPreviousData, result)
    }
}
