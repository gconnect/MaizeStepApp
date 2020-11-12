package com.chuks.maizestemapp.common.util

import android.content.Context
import android.os.Build
import android.text.Spannable
import android.text.SpannableString
import android.widget.Toast
import android.text.Spanned
import android.text.style.BulletSpan



/**
 * Use Kotlin [ExtensionFunctionType] to use [showToast] as a context extension function.
 * @param message to show on the toast
 ***/
fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

