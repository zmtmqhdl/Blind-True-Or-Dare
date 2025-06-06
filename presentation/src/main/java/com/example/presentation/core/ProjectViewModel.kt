package com.example.presentation.core

import android.util.Log
import androidx.lifecycle.ViewModel

open class ProjectViewModel(private val tag: String) : ViewModel() {
    protected fun logD(message: String) {
        Log.d(tag, message)
    }
}