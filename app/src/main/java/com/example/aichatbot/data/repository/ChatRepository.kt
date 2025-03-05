package com.example.aichatbot.data.repository

import com.example.aichatbot.model.Message

interface ChatRepository {
    suspend fun getAIResponse(userMessage: String): Message
}