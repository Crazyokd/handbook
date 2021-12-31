package com.example.handbook

object BillList {
    var bl=ArrayList<Bill>()

    fun isNumeric(input: String): Boolean =
            try {
                input.toDouble()
                true
            } catch(e: NumberFormatException) {
                false
            }
}