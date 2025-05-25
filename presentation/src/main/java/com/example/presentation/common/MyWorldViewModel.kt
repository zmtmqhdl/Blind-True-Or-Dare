package com.example.presentation.common

import android.util.Log
import androidx.lifecycle.ViewModel

open class MyWorldViewModel(private val tag: String) : ViewModel() {
    protected fun logD(message: String) {
        Log.d(tag, message)
    }
}