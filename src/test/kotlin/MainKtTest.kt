import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    fun commissionCount_ShouldNotAdd() {
        val cardType = "Vk Pay"
        val sumThisMonth = 10_000_00
        val sumTransfer = 5_000_00

        val result = commissionCount(
            cardType = cardType,
            sumThisMonth = sumThisMonth,
            sumTransfer = sumTransfer
        )

        assertEquals(0, result)
    }

    @Test
    fun commissionCount_Vk() {
        val cardType = "Vk"
        val sumTransfer = 5_000_00

        val result = commissionCount(
            cardType = cardType,
            sumTransfer = sumTransfer
        )

        assertEquals(0, result)
    }

    @Test
    fun commissionCount_Mastercard_Maestro_ShouldNotBe() {
        val cardType = "Maestro"
        val sumThisMonth = 10_000_00
        val sumTransfer = 5_000_00

        val result = commissionCount(
            cardType = cardType,
            sumThisMonth = sumThisMonth,
            sumTransfer = sumTransfer
        )

        assertEquals(0, result)
    }

    @Test
    fun commissionCount_Mastercard_Maestro_ShouldBe() {
        val cardType = "Maestro"
        val sumThisMonth = 100_000_00
        val sumTransfer = 5_000_00

        val result = commissionCount(
            cardType = cardType,
            sumThisMonth = sumThisMonth,
            sumTransfer = sumTransfer
        )

        assertEquals(50_00, result)
    }

    @Test
    fun commissionCount_Visa_Mir_Min() {
        val cardType = "Visa"
        val sumThisMonth = 10_000_00
        val sumTransfer = 1_000_00

        val result = commissionCount(
            cardType = cardType,
            sumThisMonth = sumThisMonth,
            sumTransfer = sumTransfer
        )

        assertEquals(35_00, result)
    }

    @Test
    fun commissionCount_Visa_Mir_Full() {
        val cardType = "Visa"
        val sumThisMonth = 50_000_00
        val sumTransfer = 5_000_00

        val result = commissionCount(
            cardType = cardType,
            sumThisMonth = sumThisMonth,
            sumTransfer = sumTransfer
        )

        assertEquals(37_50, result)
    }

    @Test
    fun isLimitTransfer_Vk_true() {
        val cardType = "Vk Pay"
        val sumTransfer = 20_000_00

        val result = isLimitTransfer(
            cardType = cardType,
            sumTransfer = sumTransfer
        )

        assertEquals(true, result)
    }

    @Test
    fun isLimitTransfer_Vk_false() {
        val cardType = "Vk Pay"
        val sumTransfer = 5_000_00

        val result = isLimitTransfer(
            cardType = cardType,
            sumTransfer = sumTransfer
        )

        assertEquals(false, result)
    }

    @Test
    fun isLimitTransfer_Card_true() {
        val cardType = "Visa"
        val sumTransfer = 200_000_00

        val result = isLimitTransfer(
            cardType = cardType,
            sumTransfer = sumTransfer
        )

        assertEquals(true, result)
    }

    @Test
    fun isLimitTransfer_Card_false() {
        val cardType = "Visa"
        val sumTransfer = 50_000_00

        val result = isLimitTransfer(
            cardType = cardType,
            sumTransfer = sumTransfer
        )

        assertEquals(false, result)
    }

    @Test
    fun isLimitMonth_Vk_true() {
        val cardType = "Vk Pay"
        val sumMonthPlusTransfer = 45_000_00

        val result = isLimitMonth(
            cardType = cardType,
            sumMonthPlusTransfer = sumMonthPlusTransfer
        )

        assertEquals(true, result)
    }

    @Test
    fun isLimitMonth_Vk_false() {
        val cardType = "Vk Pay"
        val sumMonthPlusTransfer = 20_000_00

        val result = isLimitMonth(
            cardType = cardType,
            sumMonthPlusTransfer = sumMonthPlusTransfer
        )

        assertEquals(false, result)
    }

    @Test
    fun isLimitMonth_Card_true() {
        val cardType = "Visa"
        val sumMonthPlusTransfer = 700_000_00

        val result = isLimitMonth(
            cardType = cardType,
            sumMonthPlusTransfer = sumMonthPlusTransfer
        )

        assertEquals(true, result)
    }

    @Test
    fun isLimitMonth_Card_false() {
        val cardType = "Visa"
        val sumMonthPlusTransfer = 400_000_00

        val result = isLimitMonth(
            cardType = cardType,
            sumMonthPlusTransfer = sumMonthPlusTransfer
        )

        assertEquals(false, result)
    }
}