package com.example.ex2
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import com.example.ex2.ui.theme.Ex2Theme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton


class MessageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val selectedLanguage = intent.getStringExtra("SELECTED_LANGUAGE") ?: "English"

        setContent {
            Ex2Theme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    messageScreen(selectedLanguage)
                }
            }
        }
    }
}

@Composable
fun messageScreen(selectedLanguage: String) {
    val messages = remember { mutableStateListOf(
        when (selectedLanguage) {
            "Portuguese" -> "Olá, como posso ajudar?"
            "Spanish" -> "Hola, ¿cómo puedo ayudar?"
            else -> "Hello! How can I help you today?"
        }
    )}
    var newMessage by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        LazyColumn(
            modifier = Modifier.weight(1f),
            reverseLayout = true // para exibir as mensagens mais recentes no final
        ) {
            items(messages.size) { index ->
                val messages = messages[messages.size - 1 - index]
                Text(
                    text = messages,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = newMessage,
                onValueChange = { newMessage = it },
                label = {
                    Text(
                        when (selectedLanguage) {
                            "Portuguese" -> "Digite uma mensagem"
                            "Spanish" -> "Escribe un mensaje"
                            else -> "Type a message"
                        }
                    )
                },
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = {
                if (newMessage.isNotBlank()) {
                    messages.add(newMessage)
                    newMessage = ""
                }
            }) {
                Icon(Icons.Filled.Send, contentDescription = "Send message")
            }
        }
    }
}






