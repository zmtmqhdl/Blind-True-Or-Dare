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

abstract class ProjectViewModel<STATE : Any, EVENT : Any>(
    initialState: STATE,
    private val viewModelTag: String = "NewBaseViewModel"
) : ViewModel() {
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<STATE> = _state.asStateFlow()

    private val _event = Channel<EVENT>(Channel.BUFFERED)
    val event: Flow<EVENT> = _event.receiveAsFlow()

    protected fun setState(reducer: STATE.() -> STATE) {
        _state.update { it.reducer() }
    }

    protected fun setEvent(event: EVENT) {
        viewModelScope.launch {
            _event.send(event)
        }
    }

    protected fun logD(message: String, tag: String = viewModelTag) {
        Log.d(tag, message)
    }
}