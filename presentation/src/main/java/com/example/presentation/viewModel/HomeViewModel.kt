package com.example.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.RetrofitData
import com.example.data.repository.RetrofitRepository
import com.example.data.retrofit.RetrofitClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.data.repository.RoomRepository
import com.example.data.room.RoomEntity
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val roomRepository: RoomRepository,
    private val retrofitRepository: RetrofitRepository
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

    fun loadUserById(id: Int) {
        viewModelScope.launch {
            val user = roomRepository.getUserById(id)
            _userText.value = user?.let { "${it.name} ${it.age}살" } ?: "User not found"
        }
    }

    fun insertUser(name: String, age: Int) {
        viewModelScope.launch {
            val user = RoomEntity(name = name, age = age)
            roomRepository.insertUser(user)
        }
    }

    private val _post = MutableStateFlow<RetrofitData?>(null)
    val post = _post.asStateFlow()

    fun fetchPost(postId: Int) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.retrofitService.get(postId)
                _post.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}