package id.novian.challengechapter8.helper

import java.text.SimpleDateFormat
import java.util.*

fun String.toDate(): String? {
    if (this.isEmpty()) {
        return "-"
    }
    val inputPattern = "yyyy-MM-dd"

    val outputPattern = "MMM dd, yyyy"

    val inputFormat = SimpleDateFormat(inputPattern, Locale.getDefault())
    val outputFormat = SimpleDateFormat(outputPattern, Locale("in"))

    val inputDate = inputFormat.parse(this)

    return inputDate?.let {
        outputFormat.format(it)
    }
}