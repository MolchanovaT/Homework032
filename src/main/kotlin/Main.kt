fun main() {
    val cardType = "Maestro"
    val sumThisMonth = 450_789_20
    val sumTransfer = 60_567_60

    val result = commissionCount(cardType, sumThisMonth, sumTransfer)
    val rubles = result / 100
    val cents = result % 100
    println("Total commission: $rubles.$cents rub")
}

fun commissionCount(cardType: String = "Vk Pay", sumThisMonth: Int = 0, sumTransfer: Int): Int {
    val commissionMastercard = 60
    val commissionMastercardPlusSum = 20_00
    val commissionMastercardMaxTransfer = 75_000_00

    val commissionVisa = 75
    val commissionVisaMin = 35_00

    val vkLimitMonth = 40_000_00
    val vkLimitTransfer = 15_000_00

    val cardsLimitMonth = 600_000_00
    val cardsLimitTransfer = 150_000_00

    return when (cardType) {
        "Vk Pay" -> {

            if (sumTransfer > vkLimitTransfer) {
                println("INFO: Exceeded the transfer limit per day")
                return 0
            }

            if (sumThisMonth + sumTransfer > vkLimitMonth) {
                println("INFO: Exceeded the transfer limit per month")
                return 0
            }

            0
        }
        "Mastercard", "Maestro" -> {

            if (sumTransfer > cardsLimitTransfer) {
                println("INFO: Exceeded the transfer limit per day")
                return 0
            }

            if (sumThisMonth + sumTransfer > cardsLimitMonth) {
                println("INFO: Exceeded the transfer limit per month")
                return 0
            }

            if (sumThisMonth + sumTransfer <= commissionMastercardMaxTransfer) 0
            else sumTransfer * commissionMastercard / 100 / 100 + commissionMastercardPlusSum
        }
        "Visa", "Мир" -> {

            if (sumTransfer > cardsLimitTransfer) {
                println("INFO: Exceeded the transfer limit per day")
                return 0
            }

            if (sumThisMonth + sumTransfer > cardsLimitMonth) {
                println("INFO: Exceeded the transfer limit per month")
                return 0
            }

            if (sumTransfer * commissionVisa / 100 / 100 < commissionVisaMin) commissionVisaMin else sumTransfer * commissionVisa / 100 / 100
        }
        else -> 0
    }
}