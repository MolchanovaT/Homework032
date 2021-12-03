const val commissionMastercard = 60
const val commissionMastercardPlusSum = 20_00
const val commissionMastercardMaxTransfer = 75_000_00

const val commissionVisa = 75
const val commissionVisaMin = 35_00

const val vkLimitMonth = 40_000_00
const val vkLimitTransfer = 15_000_00

const val cardsLimitMonth = 600_000_00
const val cardsLimitTransfer = 150_000_00

fun main() {
    val cardType = "Visa"
    val sumThisMonth = 50_000_00
    val sumTransfer = 5_000_00

    val result = commissionCount(cardType, sumThisMonth, sumTransfer)
    val rubles = result / 100
    val cents = result % 100
    println("Total commission: $rubles.$cents rub")
}

fun commissionCount(cardType: String = "Vk Pay", sumThisMonth: Int = 0, sumTransfer: Int): Int {
    if (isLimitTransfer(cardType, sumTransfer)) {
        println("INFO: Exceeded the transfer limit per day")
        return 0
    }

    if (isLimitMonth(cardType, sumThisMonth + sumTransfer)) {
        println("INFO: Exceeded the transfer limit per month")
        return 0
    }

    return when (cardType) {
        "Vk Pay" -> {
            0
        }
        "Mastercard", "Maestro" -> {
            if (sumThisMonth + sumTransfer <= commissionMastercardMaxTransfer) 0
            else sumTransfer * commissionMastercard / 100 / 100 + commissionMastercardPlusSum
        }
        "Visa", "Мир" -> {
            if (sumTransfer * commissionVisa / 100 / 100 < commissionVisaMin) commissionVisaMin else sumTransfer * commissionVisa / 100 / 100
        }
        else -> 0
    }
}

fun isLimitTransfer(cardType: String, sumTransfer: Int): Boolean {
    return when (cardType) {
        "Vk Pay" -> {
            sumTransfer > vkLimitTransfer
        }
        "Mastercard", "Maestro", "Visa", "Мир" -> {
            sumTransfer > cardsLimitTransfer
        }
        else -> false
    }
}

fun isLimitMonth(cardType: String, sumMonthPlusTransfer: Int): Boolean {
    return when (cardType) {
        "Vk Pay" -> {
            sumMonthPlusTransfer > vkLimitMonth
        }
        "Mastercard", "Maestro", "Visa", "Мир" -> {
            sumMonthPlusTransfer > cardsLimitMonth
        }
        else -> false
    }
}
