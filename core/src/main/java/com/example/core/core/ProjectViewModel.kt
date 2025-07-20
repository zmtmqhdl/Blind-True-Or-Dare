package com.example.core.core

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class ProjectViewModel(
    private val viewModelTag: String = "NewBaseViewModel"
) : ViewModel() {

    protected fun logD(message: String, tag: String = viewModelTag) {
        Log.d(tag, message)
    }
}