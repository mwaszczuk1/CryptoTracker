package pl.mwaszczuk.formatters

import org.junit.Assert
import org.junit.Test

class BigNumberFormatterTest {

    private val formatter = BigNumberFormatter()

    @Test
    fun `test number formatting`() {
        val a = 1000.0
        val b = 12345678.0
        val c = 1234567890.0

        Assert.assertEquals("1.0k", formatter.format(a))
        Assert.assertEquals("12.3M", formatter.format(b))
        Assert.assertEquals("1.2B", formatter.format(c))
    }
}
