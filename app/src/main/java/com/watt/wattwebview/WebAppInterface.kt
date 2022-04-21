package com.watt.wattwebview

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.webkit.JavascriptInterface
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService

/**
 * Created by khm on 2022-01-26.
 */

class WebAppInterface(private val mActivity: AppCompatActivity) {
    @JavascriptInterface
    fun sendAndroidMsg(msg:String){
        if(msg == "Enter"){
            val imm: InputMethodManager = mActivity.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(mActivity.currentFocus?.windowToken, 0)
        }
    }
}