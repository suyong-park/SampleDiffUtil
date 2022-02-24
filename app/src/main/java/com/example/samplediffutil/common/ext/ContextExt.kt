package com.example.samplediffutil.common.ext

import android.content.Context
import android.widget.Toast

fun Context.showToast(msg: String, isShort: Boolean = true) {
    val length = if (isShort) Toast.LENGTH_SHORT else Toast.LENGTH_LONG
    Toast.makeText(this, msg, length).show()
}

fun Context.showToast(strRes: Int, isShort: Boolean = true) {
    val length = if (isShort) Toast.LENGTH_SHORT else Toast.LENGTH_LONG
    Toast.makeText(this, strRes, length).show()
}

fun Context.showToast(strRes: Int, varargs: Any, isShort: Boolean = true) {
    val msg = getString(strRes, varargs)
    val length = if (isShort) Toast.LENGTH_SHORT else Toast.LENGTH_LONG
    Toast.makeText(this, msg, length).show()
}