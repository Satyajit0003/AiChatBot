package com.example.aichatbot.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.aichatbot.model.Message

@Composable
fun MessageBubble(message: Message) {
    val backgroundColor = if (message.isUser) Color(0xFFDCF8C6) else Color(0xFFE5E5EA)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = if (message.isUser) Arrangement.End else Arrangement.Start
    ) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = backgroundColor,
            modifier = Modifier.padding(4.dp)
        ) {
            Text(
                text = message.text,
                modifier = Modifier.padding(8.dp),
                color = Color.Black
            )
        }
    }
}


