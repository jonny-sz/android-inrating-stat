package com.jonnydev.statistics.extension

import android.util.Log

fun Any.logd(message: String) {
    Log.d(this::class.java.simpleName, message)
}
