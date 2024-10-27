package com.example.ex2
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.ex2.ui.theme.Ex2Theme


class EmailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Recebendo o idioma selecionado
        val selectedLanguage = intent.getStringExtra("SELECTED_LANGUAGE") ?: "English"

        setContent {
            Ex2Theme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    // Usando o idioma selecionado para mostrar o texto apropriado
                    val emailText = when (selectedLanguage) {

                        "Portuguese" -> "Esta é a tela de Emails"
                        "English" -> "This is the Email screen"
                        "Spanish" -> "Esta es la pantalla de correos"
                        else -> "This is the Email screen" // default
                    }
                    Text(emailText) // Customize your content here
                }
            }
        }
    }
}



