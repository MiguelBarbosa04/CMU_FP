package com.example.ex2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import com.example.ex2.ui.theme.Ex2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ex2Theme {
                MessageCardScreen()
            }
        }
    }
}

data class Message(val author: String, val body: String)

// Mapa para armazenar as traduções
private val translations = mapOf(
    "English" to Translation(
        title = "Ali Connors",
        personalPhone = "(Personal)",
        workPhone = "(Work)",
        emailPersonal = "(Personal)",
        emailWork = "(Work)",
        languageSelectionTitle = "Select Language",
        closeButton = "Close",
        calling = "Calling ",
        languageSelected = "Language selected: ",
        loginButton = "Login"

    ),
    "Portuguese" to Translation(
        title = "Ali Connors",
        personalPhone = "(Pessoal)",
        workPhone = "(Trabalho)",
        emailPersonal = "(Pessoal)",
        emailWork = "(Trabalho)",
        languageSelectionTitle = "Selecionar Idioma",
        closeButton = "Fechar",
        calling = "A ligar para ",
        languageSelected = "Idioma selecionado: ",
        loginButton = "Login"
    ),
    "Spanish" to Translation(
        title = "Ali Connors",
        personalPhone = "(Personal)",
        workPhone = "(Trabajo)",
        emailPersonal = "(Personal)",
        emailWork = "(Trabajo)",
        languageSelectionTitle = "Seleccionar Idioma",
        closeButton = "Cerrar",
        calling = "Llamando a ",
        languageSelected = "Idioma seleccionado: ",
        loginButton = "Login"
    )
)

data class Translation(
    val title: String,
    val personalPhone: String,
    val workPhone: String,
    val emailPersonal: String,
    val emailWork: String,
    val languageSelectionTitle: String,
    val closeButton: String,
    val calling: String,
    val languageSelected: String,
    val loginButton: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageCardScreen() {
    val context = LocalContext.current
    var selectedLanguage by remember { mutableStateOf("English") } // Estado para o idioma selecionado
    val languages = listOf("English", "Portuguese", "Spanish") // Idiomas disponíveis
    var showDialog by remember { mutableStateOf(false) } // Estado para controlar o diálogo
    val translationsMap = translations[selectedLanguage] ?: translations["English"]!! // Mapeia as traduções

    Scaffold(
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Image(
                    painter = painterResource(R.drawable.profile_picture),
                    contentDescription = "Contacto profile picture",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentScale = ContentScale.FillWidth
                )

                // Botão de voltar
                IconButton(
                    onClick = {
                        if (context is ComponentActivity) {
                            context.finish() // Volta à tela anterior
                        }
                    },
                    modifier = Modifier.align(Alignment.TopStart)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Voltar",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }

                // Botão de seleção de idioma
                IconButton(
                    onClick = { showDialog = true },
                    modifier = Modifier.align(Alignment.TopCenter)
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Selecionar Idioma",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }

                // Diálogo de seleção de idioma
                if (showDialog) {
                    LanguageSelectionDialog(
                        languages = languages,
                        onDismiss = { showDialog = false },
                        onLanguageChange = {
                            selectedLanguage = it
                            showDialog = false
                            Toast.makeText(context, translations[selectedLanguage]?.languageSelected + it, Toast.LENGTH_SHORT).show()
                        },
                        dialogTitle = translationsMap.languageSelectionTitle,
                        closeButtonText = translationsMap.closeButton
                    )
                }

                Column(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .offset(y = 230.dp)
                        .padding(20.dp)
                ) {
                    Text(
                        text = translationsMap.title,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = Color.White,
                            fontSize = 30.sp
                        )
                    )

                    ContactInfoRow(
                        phone = "(123) 456-7890",
                        phoneLabel = translationsMap.personalPhone,
                        onPhoneClick = {
                            Toast.makeText(context, translationsMap.calling + "(123) 456-7890...", Toast.LENGTH_SHORT).show()
                        },
                        onMessageClick = {
                            val intent = Intent(context, MessageActivity::class.java).apply {
                                putExtra("SELECTED_LANGUAGE", selectedLanguage) // Passando o idioma selecionado
                            }
                            context.startActivity(intent)
                        }
                    )

                    ContactInfoRow(
                        phone = "456-7890",
                        phoneLabel = translationsMap.workPhone,
                        onPhoneClick = {
                            Toast.makeText(context, translationsMap.calling + "456-7890...", Toast.LENGTH_SHORT).show()
                        }
                    )

                    EmailRow(
                        email = "aliconnors@example.com",
                        emailLabel = translationsMap.emailPersonal,
                        onEmailClick = {
                            val intent = Intent(context, EmailActivity::class.java).apply {
                                putExtra("SELECTED_LANGUAGE", selectedLanguage) // Passando o idioma selecionado
                            }
                            context.startActivity(intent)
                        }
                    )

                    EmailRow(
                        email = "aliconnorsWork@example.com",
                        emailLabel = translationsMap.emailWork,
                        onEmailClick = {
                            val intent = Intent(context, EmailActivity::class.java).apply {
                                putExtra("SELECTED_LANGUAGE", selectedLanguage) // Passando o idioma selecionado
                            }
                            context.startActivity(intent)
                        }
                    )

                    LoginRow(
                        login = "Faz Login",
                        loginLabel = translationsMap.loginButton,
                        onLoginClick = {
                            val intent = Intent(context, LoginActivity::class.java).apply {
                                putExtra("SELECTED_LANGUAGE", selectedLanguage) // Passando o idioma selecionado
                            }
                            context.startActivity(intent)
                        }


                    )

                }
            }
        }
    )
}

@Composable
fun LanguageSelectionDialog(languages: List<String>, onDismiss: () -> Unit, onLanguageChange: (String) -> Unit, dialogTitle: String, closeButtonText: String) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(dialogTitle) },
        text = {
            Column {
                languages.forEach { language ->
                    Text(
                        text = language,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onLanguageChange(language)
                            }
                            .padding(8.dp)
                    )
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(closeButtonText)
            }
        }
    )
}

@Composable
fun ContactInfoRow(
    phone: String,
    phoneLabel: String,
    onPhoneClick: () -> Unit,
    onMessageClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .padding(top = 25.dp)
            .fillMaxWidth()
            .clickable { onPhoneClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Phone,
            contentDescription = "Telefone",
            modifier = Modifier
                .size(50.dp)
                .padding(end = 20.dp),
            tint = Color.Blue
        )
        Column {
            Text(
                text = phone,
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp)
            )
            Text(
                text = phoneLabel,
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                modifier = Modifier.padding(top = 1.dp)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        onMessageClick?.let {
            Icon(
                imageVector = Icons.Default.MailOutline,
                contentDescription = "Mensagem",
                modifier = Modifier
                    .size(50.dp)
                    .padding(end = 20.dp)
                    .clickable { it() },
                tint = Color.Blue
            )
        }
    }
}

@Composable
fun EmailRow(
    email: String,
    emailLabel: String,
    onEmailClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(top = 25.dp)
            .fillMaxWidth()
            .clickable { onEmailClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Email,
            contentDescription = "Email",
            modifier = Modifier
                .size(50.dp)
                .padding(end = 20.dp),
            tint = Color.Blue
        )
        Column {
            Text(
                text = email,
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp)
            )
            Text(
                text = emailLabel,
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                modifier = Modifier.padding(top = 1.dp)
            )
        }
    }
}

@Composable
fun LoginRow(
    login: String,
    loginLabel: String,
    onLoginClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(top = 25.dp)
            .fillMaxWidth()
            .clickable { onLoginClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.AccountBox,
            contentDescription = "Login",
            modifier = Modifier
                .size(50.dp)
                .padding(end = 20.dp),
            tint = Color.Blue
        )
        Column {
            Text(
                text = login,
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp)
            )
            Text(
                text = loginLabel,
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                modifier = Modifier.padding(top = 1.dp)
            )
        }
    }
}

@Preview
@Composable
fun GreetingPreview() {
    MessageCardScreen()
}
