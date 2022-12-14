package id.ac.stiki.doleno.absenin.util.date

import java.text.SimpleDateFormat
import java.util.*

class DateUtil {
    companion object {
        private val monthName = listOf(
            "Januari",
            "Februari",
            "Maret",
            "April",
            "Mei",
            "Juni",
            "Juli",
            "Agustus",
            "September",
            "Oktober",
            "November",
            "Desember"
        )

        fun getMonthName(month: Int): String {
            return monthName[month]
        }

        fun dateToString(date: Date): String {
            val dateFormat = SimpleDateFormat("dd MMMM yyyy")
            return dateFormat.format(date)
        }

        fun dateFromString(date: String): Date {
            return SimpleDateFormat("dd MMMM yyyy").parse(date) ?: Date()
        }

        fun removeTime(date: Date): Date {
            val cal = Calendar.getInstance()
            cal.time = date
            cal[Calendar.HOUR_OF_DAY] = 0
            cal[Calendar.MINUTE] = 0
            cal[Calendar.SECOND] = 0
            cal[Calendar.MILLISECOND] = 0
            return cal.time
        }
    }
}

fun Date.dateToString(format: String): String {
    //simple date formatter
    val dateFormatter = SimpleDateFormat(format, Locale.getDefault())

    //return the formatted date string
    return dateFormatter.format(this)
}