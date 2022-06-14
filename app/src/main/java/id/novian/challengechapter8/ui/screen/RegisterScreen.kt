package id.novian.challengechapter8.ui.screen

import androidx.compose.foundation.Image
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
import id.novian.challengechapter8.model.local.profile.Profile
import id.novian.challengechapter8.viewmodel.RegisterScreenViewModel

@Composable
fun RegisterScreen(navController: NavHostController, viewModel: RegisterScreenViewModel) {
    viewModel.getEmailAndUsername()

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

        OutlinedTextField(
            value = viewModel.username,
            onValueChange = { viewModel.usernameOnChange(it) },
            singleLine = true,
            label = { Text(text = "Masukkan Username") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 40.dp)
        )

        OutlinedTextField(
            value = viewModel.email,
            onValueChange = { viewModel.emailOnChange(it) },
            singleLine = true,
            label = { Text(text = "Masukkan Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
        )

        OutlinedTextField(
            value = viewModel.password,
            onValueChange = { viewModel.passwordOnChange(it) },
            label = { Text(text = "Masukkan Password") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (viewModel.passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image =
                    if (viewModel.passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                val description =
                    if (viewModel.passwordVisible) "Hide Password" else "Show Password"

                IconButton(onClick = { viewModel.passwordVisible = !viewModel.passwordVisible }) {
                    Icon(imageVector = image, contentDescription = description)
                }
            }
        )

        OutlinedTextField(
            value = viewModel.confPassword,
            onValueChange = { viewModel.confPassOnChange(it) },
            label = { Text(text = "Masukkan Konfirmasi Password") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (viewModel.confPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image =
                    if (viewModel.confPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                val description =
                    if (viewModel.confPasswordVisible) "Hide Password" else "Show Password"

                IconButton(onClick = {
                    viewModel.confPasswordVisible = !viewModel.confPasswordVisible
                }) {
                    Icon(imageVector = image, contentDescription = description)
                }
            }
        )


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
                .padding(start = 16.dp, end = 16.dp, top = 48.dp),
            enabled = viewModel.username.isNotBlank() && viewModel.email.isNotBlank() && viewModel.password.isNotBlank() && viewModel.confPassword == viewModel.password && viewModel.checked
        ) {
            Text(text = "Register")
            viewModel.insertProfile(
                Profile(
                    null,
                    viewModel.username,
                    viewModel.email,
                    viewModel.password
                )
            )
        }

    }


}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    val viewModel = hiltViewModel<RegisterScreenViewModel>()
    RegisterScreen(rememberNavController(), viewModel)
}