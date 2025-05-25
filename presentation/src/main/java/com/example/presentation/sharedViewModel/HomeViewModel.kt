package com.example.presentation.sharedViewModel

import androidx.lifecycle.ViewModel
import com.example.data.model.RetrofitDataDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
) : ViewModel() {
    private val _sheetState = MutableStateFlow(false)
    val sheetState: StateFlow<Boolean> = _sheetState

    fun showSheet() {
        _sheetState.value = true
    }

    fun hideSheet() {
        _sheetState.value = false
    }

    private val _userText = MutableStateFlow("Loading...")
    val userText: StateFlow<String> = _userText


    private val _post = MutableStateFlow<RetrofitDataDto?>(null)
    val post = _post.asStateFlow()

}