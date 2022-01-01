package com.example.handbook

import java.io.Serializable
import java.sql.Time
import java.util.*

class Bill(
    val money: Double?,
    val label: String,
    var comment: String?,
    val calendar: String?,
    val ctgr: Int
) : Serializable {

    companion object {
        const val TYPE_INCOME = 1
        const val TYPE_EXPENSE = 0
    }
}