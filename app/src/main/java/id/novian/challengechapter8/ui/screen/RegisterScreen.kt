package id.novian.challengechapter8.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import id.novian.challengechapter8.R

@Composable
fun RegisterScreen(navController: NavHostController) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        Text(
            text = "Register",
            fontSize = MaterialTheme.typography.h4.fontSize,
            modifier = Modifier
                .padding(top = 48.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.ic_logo_fox),
            contentDescription = "",
            modifier = Modifier
                .width(188.dp)
                .height(141.dp)
                .padding(top = 32.dp)
        )

        TextFieldEdited(label = "Masukkan Username", paddingTop = 40.dp)
        TextFieldEdited(label = "Masukkan Email", paddingTop = 16.dp)
        TextFieldPasswordAndConfirm(label = "Masukkan Password")
        TextFieldPasswordAndConfirm(label = "Masukkan Konfirmasi Password")

        Button(
            onClick = {
                navController.navigate("login") {
                    popUpTo("register") {
                        inclusive = true
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 48.dp)
        ) {
            Text(text = "Register")
        }

    }


}

@Composable
fun TextFieldEdited(label: String, paddingTop: Dp) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        singleLine = true,
        label = { Text(text = label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = paddingTop)
    )
}


@Composable
fun TextFieldPasswordAndConfirm(label: String) {
    var text by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text(text = label) },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
            val description = if (passwordVisible) "Hide Password" else "Show Password"

            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = image, contentDescription = description)
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(rememberNavController())
}