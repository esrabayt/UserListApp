package com.esrakaya.userlistapp.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackbar(messageRes: String, length: Int = Snackbar.LENGTH_LONG) {
    Snackbar.make(this, messageRes, length).show()
}