package com.zeynelerdi.mvpgithub.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

object KeyboardUtils {

    fun hideKeyboard(context: Context) {
        try {
            val inputMethodManager =
                    context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(
                    (context as Activity).currentFocus!!.windowToken,
                    0
            )
        } catch (e: Exception) {

        }

    }

    fun hideKeyboard(v: View, context: Context) {
        try {
            val inputMethodManager =
                    context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(v.windowToken, 0)
        } catch (e: Exception) {

        }

    }

    fun showKeyboard(editText: EditText, context: Context) {
        try {
            val inputMethodManager =
                    context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.showSoftInput(editText, InputMethodManager.SHOW_FORCED)
        } catch (e: Exception) {

        }

    }
}