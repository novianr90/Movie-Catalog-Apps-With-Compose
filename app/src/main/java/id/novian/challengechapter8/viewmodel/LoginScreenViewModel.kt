package id.novian.challengechapter8.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    //Check
    private var _checked = MutableLiveData<Boolean>()
    val checked: LiveData<Boolean> get() = _checked

    private var _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun checkEmailAndPassword(email: String, pass: String) {
        viewModelScope.launch {
            val emailData = localRepository.getEmail(email)
            val passwordData = localRepository.getPassword(pass)

            if (emailData.isNullOrEmpty() || passwordData.isNullOrEmpty()) {
                _checked.postValue(false)
                _error.postValue("Profile Already Exist")
            } else {
                _checked.postValue(true)
                sharedPreferences.saveEmail(email)
                _error.postValue("Welcome, $email")
            }
        }
    }
}