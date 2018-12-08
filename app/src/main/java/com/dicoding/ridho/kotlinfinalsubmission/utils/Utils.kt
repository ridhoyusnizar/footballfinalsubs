package com.dicoding.ridho.kotlinfinalsubmission.utils

import android.view.View
import java.text.SimpleDateFormat
import java.util.*

fun View.visible() {
    visibility = View.VISIBLE
}
fun View.invisible() {
    visibility = View.INVISIBLE
}

fun formatUTCtoGMT(date: String, time: String, format: String): String {
    val date = "$date $time +0000"
    val oldFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm Z")
    oldFormatter.timeZone = TimeZone.getTimeZone("UTC")
    val parser = SimpleDateFormat(format)
    val value = oldFormatter.parse(date)
    parser.timeZone = TimeZone.getDefault()
    val result = parser.format(value)
    return result
}

