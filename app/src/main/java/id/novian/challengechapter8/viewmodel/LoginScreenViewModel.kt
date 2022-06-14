package id.novian.challengechapter8.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.novian.challengechapter8.helper.SharedPreferences
import id.novian.challengechapter8.repository.LocalRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel
@Inject constructor(
    private val localRepository: LocalRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    //Username
    var email by mutableStateOf("")

    fun emailOnChange(newString: String) {
        email = newString
    }

    //Password
    var password by mutableStateOf("")
    var passwordVisible by mutableStateOf(false)

    fun passwordOnChange(newString: String) {
        password = newString
    }

    //Check
    var checked by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf("")
        private set

    fun checkEmailAndPassword() {
        viewModelScope.launch {
            val emailData = localRepository.getEmail(email)
            val passwordData = localRepository.getPassword(password)

            if (emailData.isNullOrEmpty() || passwordData.isNullOrEmpty()) {
                checked = false
                errorMessage = "Profile Not Found"
            } else {
                checked = true
                sharedPreferences.saveEmail(email)
                sharedPreferences.saveStatusLogin()
            }
        }
    }
}