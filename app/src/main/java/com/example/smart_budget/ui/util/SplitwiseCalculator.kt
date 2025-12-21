package com.example.smart_budget.ui.util

/**
 * Utility object to calculate how much each participant owes in a group expense
 * (Splitwise-style equal split).
 */
object SplitwiseCalculator {

    /**
     * Calculates how much each participant should pay.
     *
     * @param totalAmount Total expense amount
     * @param participantEmails List of users involved in the expense
     * @return Map of user email -> amount owed
     */
    fun calculateSplit(
        totalAmount: Double,
        participantEmails: List<String>
    ): Map<String, Double> {

        if (participantEmails.isEmpty()) {
            return emptyMap()
        }

        val splitAmount = totalAmount / participantEmails.size

        return participantEmails.associateWith {
            // Round to 2 decimal places
            String.format("%.2f", splitAmount).toDouble()
        }
    }
}
