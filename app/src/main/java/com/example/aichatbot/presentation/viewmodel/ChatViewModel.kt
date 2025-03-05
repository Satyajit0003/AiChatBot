package com.example.aichatbot.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aichatbot.data.repository.ChatRepository
import com.example.aichatbot.data.repository.ChatRepositoryImpl
import com.example.aichatbot.model.Message
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChatViewModel(private val chatRepository: ChatRepository = ChatRepositoryImpl()) : ViewModel() {

    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages = _messages.asStateFlow()

    fun sendMessage(userMessage: String) {
        val updatedMessages = _messages.value.toMutableList().apply {
            add(Message(userMessage, true))
        }
        _messages.value = updatedMessages

        viewModelScope.launch {
            val aiResponse = chatRepository.getAIResponse(userMessage)
            _messages.value = _messages.value + aiResponse
        }
    }
}
