package id.novian.challengechapter8.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.novian.challengechapter8.model.local.Profile
import id.novian.challengechapter8.repository.LocalRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterScreenViewModel @Inject constructor(private val localRepository: LocalRepository) :
    ViewModel() {

    fun insertProfile(profile: Profile) {
        viewModelScope.launch {
            localRepository.insertProfile(profile)
        }
    }

    //Check profile
    private var _checked = MutableLiveData<Boolean>()
    val checked: LiveData<Boolean> get() = _checked

    fun getEmailAndUsername(email: String, username: String) {
        viewModelScope.launch {
            val emailData = localRepository.getEmail(email)
            val usernameData = localRepository.getUsername(username)

            if (!emailData.isNullOrEmpty() || !usernameData.isNullOrEmpty()) {
                _checked.postValue(false)
            } else {
                _checked.postValue(true)
            }
        }
    }


}