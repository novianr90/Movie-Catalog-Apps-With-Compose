package id.novian.challengechapter8.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import id.novian.challengechapter8.R
import id.novian.challengechapter8.viewmodel.LoginScreenViewModel


@Composable
fun LoginScreen(navController: NavHostController, viewModel: LoginScreenViewModel) {

    viewModel.checkEmailAndPassword()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .fillMaxSize()
    ) {

        Text(
            text = "Login",
            fontSize = MaterialTheme.typography.h4.fontSize,
            modifier = Modifier.padding(top = 48.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.ic_logo_fox),
            contentDescription = "",
            modifier = Modifier
                .width(188.dp)
                .height(141.dp)
                .padding(top = 32.dp)
        )


        OutlinedTextField(
            value = viewModel.email,
            onValueChange = { viewModel.emailOnChange(it) },
            label = { Text(text = "Masukkan Username") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 40.dp)
        )

        OutlinedTextField(
            value = viewModel.password,
            onValueChange = { viewModel.passwordOnChange(it) },
            singleLine = true,
            label = { Text(text = "Masukkan Password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (viewModel.passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image =
                    if (viewModel.passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                val description =
                    if (viewModel.passwordVisible) "Hide Password" else "Show Password"

                IconButton(onClick = { viewModel.passwordVisible = !viewModel.passwordVisible }) {
                    Icon(imageVector = image, description)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
        )

        Button(
            onClick = {
                navController.navigate("home") {
                    popUpTo("login") {
                        inclusive = true
                    }
                }
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 150.dp, start = 16.dp, end = 16.dp),
            enabled = viewModel.email.isNotBlank() && viewModel.password.isNotBlank() && viewModel.checked
        ) {
            Text(text = "Login")
        }

        Text(text = "Belum punya akun?", modifier = Modifier
            .clickable { }
            .padding(top = 8.dp)
            .clickable { navController.navigate("register") })

    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val viewModel = hiltViewModel<LoginScreenViewModel>()
    LoginScreen(navController = rememberNavController(), viewModel)
}