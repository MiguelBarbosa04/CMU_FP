package com.example.ex2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.example.ex2.ui.theme.Ex2Theme
import androidx.compose.runtime.*





class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val selectedLanguage = intent.getStringExtra("SELECTED_LANGUAGE") ?: "English"

        setContent {
            Ex2Theme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    LoginScreen(selectedLanguage)
                }
            }
        }
    }
}


@Composable
fun LoginScreen(selectedLanguage: String) {

    // Definindo os textos com base no idioma selecionado
    val loginText = when (selectedLanguage) {
        "Portuguese" -> "Entrar"
        "Spanish" -> "Iniciar sesión"
        else -> "Login"
    }

    val enterUsernamePasswordText = when (selectedLanguage) {
        "Portuguese" -> "Digite seu nome de usuário e senha para entrar"
        "Spanish" -> "Ingrese su nombre de usuario y contraseña para iniciar sesión"
        else -> "Enter your username and password to login"
    }

    val usernamePlaceholder = when (selectedLanguage) {
        "Portuguese" -> "Nome de usuário"
        "Spanish" -> "Nombre de usuario"
        else -> "Username"
    }

    val passwordPlaceholder = when (selectedLanguage) {
        "Portuguese" -> "Senha"
        "Spanish" -> "Contraseña"
        else -> "Password"
    }

    val forgotUsernameText = when (selectedLanguage) {
        "Portuguese" -> "Esqueceu o nome de usuário?"
        "Spanish" -> "¿Olvidó su nombre de usuario?"
        else -> "Forgot Username?"
    }

    val forgotPasswordText = when (selectedLanguage) {
        "Portuguese" -> "Esqueceu a senha?"
        "Spanish" -> "¿Olvidó su contraseña?"
        else -> "Forgot Password?"
    }

    val registerText = when (selectedLanguage) {
        "Portuguese" -> "Registrar"
        "Spanish" -> "Registrarse"
        else -> "Register"
    }

    val helpCenterText = when (selectedLanguage) {
        "Portuguese" -> "Centro de ajuda"
        "Spanish" -> "Centro de ayuda"
        else -> "Help Center"
    }

   var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    // Layout da tela de login
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.profile_picture),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = loginText,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = enterUsernamePasswordText,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = username,
            onValueChange = {username = it},
            label = { Text(usernamePlaceholder) },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                Text(
                    text = forgotUsernameText,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable { /* ação ao clicar */ }
                )
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            label = { Text(passwordPlaceholder) },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                Text(
                    text = forgotPasswordText,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable { /* ação ao clicar */ }
                )
            },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /* ação ao clicar no botão de login */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = loginText)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Don't have an account? ")
            Text(
                text = registerText,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable { /* ação ao clicar no registo */ }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Need help? Visit our ")
            Text(
                text = helpCenterText,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable { /* ação ao clicar no centro de ajuda */ }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "PT | ENG | ES",
                color = Color.Gray,
                modifier = Modifier.clickable { /*trocar de idioma*/}
            )
        }
    }
}
