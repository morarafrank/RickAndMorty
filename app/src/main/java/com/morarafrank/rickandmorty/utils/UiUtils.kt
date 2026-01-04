package com.morarafrank.rickandmorty.utils

import java.text.SimpleDateFormat
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

object UiUtils {
    fun formatLastUpdated(dt: String?): String {
        if (dt.isNullOrBlank()) return ""

        val parser = SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            Locale.getDefault()
        ).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }

        val date = parser.parse(dt) ?: return ""

        val calendar = Calendar.getInstance().apply {
            time = date
        }

        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val suffix = getDaySuffix(day)

        val formatter = SimpleDateFormat(
            "MMMM yyyy, h:mm a",
            Locale.getDefault()
        )

        return "$day$suffix ${formatter.format(date)}"
    }


    private fun getDaySuffix(day: Int): String =
        if (day in 11..13) "th"
        else when (day % 10) {
            1 -> "st"
            2 -> "nd"
            3 -> "rd"
            else -> "th"
        }

}