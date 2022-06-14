package id.novian.challengechapter8.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.novian.challengechapter8.model.local.profile.Profile
import id.novian.challengechapter8.repository.LocalRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterScreenViewModel @Inject constructor(private val localRepository: LocalRepository) :
    ViewModel() {

    //Email
    var email by mutableStateOf("")

    fun emailOnChange(newString: String) {
        email = newString
    }


    //Username
    var username by mutableStateOf("")

    fun usernameOnChange(newString: String) {
        username = newString
    }

    //Pass
    var password by mutableStateOf("")

    fun passwordOnChange(newString: String) {
        password = newString
    }

    //Confirm Pass
    var confPassword by mutableStateOf("")

    fun confPassOnChange(newString: String) {
        confPassword = newString
    }

    //Password Watcher
    var passwordVisible by mutableStateOf(false)

    var confPasswordVisible by mutableStateOf(false)

    fun insertProfile(profile: Profile) {
        viewModelScope.launch {
            localRepository.insertProfile(profile)
        }
    }

    //Check profile
    var checked by mutableStateOf(false)
        private set

    fun getEmailAndUsername() {
        viewModelScope.launch {
            val emailData = localRepository.getEmail(email)
            val usernameData = localRepository.getUsername(username)

            if (emailData.isNullOrEmpty() || usernameData.isNullOrEmpty()) {
                checked = true
            }
        }
    }


}