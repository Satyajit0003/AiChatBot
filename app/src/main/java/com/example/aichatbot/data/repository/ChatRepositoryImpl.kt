package com.example.aichatbot.data.repository

import android.util.Log
import com.example.aichatbot.model.Message
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ChatRepositoryImpl : ChatRepository {

    private val apiKey = "AIzaSyC0kmuEMqcTuRaK8U2OdGMLVMxhzxgSgZU"
    private val generativeModel = GenerativeModel(
        modelName = "gemini-1.5-pro", // Updated model name
        apiKey = apiKey
    )

    override suspend fun getAIResponse(userMessage: String): Message {
        return withContext(Dispatchers.IO) { // Ensures network calls are on IO thread
            try {
                Log.d("ChatRepository", "Sending request: $userMessage")
                val response = generativeModel.generateContent(content { text(userMessage) })
                Log.d("ChatRepository", "API Response: $response")
                val aiResponse = response.text ?: "Error: No response from AI"
                Message(aiResponse, false)
            } catch (e: Exception) {
                Log.e("ChatRepository", "Error: ${e.localizedMessage}", e)
                Message("Error: ${e.localizedMessage ?: "Unknown error"}", false)
            }
        }
    }
}
