package app.newsly.core.model.extension

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

fun String.toDate(): Date? {
    return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()).parse(this)
}

fun Date.differenceWithToday(): String {
    val diffInMillisec: Long = Date().time - this.time

    val diffInDays: Long = TimeUnit.MILLISECONDS.toDays(diffInMillisec)
    val diffInHours: Long = TimeUnit.MILLISECONDS.toHours(diffInMillisec)
    val diffInMin: Long = TimeUnit.MILLISECONDS.toMinutes(diffInMillisec)
    val diffInSec: Long = TimeUnit.MILLISECONDS.toSeconds(diffInMillisec)

    val result: String =
        if (diffInDays != 0L) {
            "$diffInDays day"
        } else if (diffInHours != 0L) {
            "$diffInHours hour"
        } else if (diffInMin != 0L) {
            "$diffInMin min"
        } else if (diffInSec != 0L) {
            "$diffInSec sec"
        } else {
            "Nothing"
        }

    return result
}