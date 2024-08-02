package com.example.presentation.util

import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.DateTimeParseException


object StringFormatter {
    private val DEFAULT_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
    private val TIME_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")
    private val DATE_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    private val MONTH_DAY_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("MM-dd")

    fun formatToMonthDay(dateTimeString: String): String {
        return try {
            val dateTime = LocalDateTime.parse(dateTimeString, DEFAULT_FORMATTER)
            dateTime.format(MONTH_DAY_FORMATTER)
        } catch (e: DateTimeParseException) {
            "Invalid date format"
        }
    }
}