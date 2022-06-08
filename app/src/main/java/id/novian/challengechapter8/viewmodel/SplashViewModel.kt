package id.novian.challengechapter8.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.novian.challengechapter8.helper.SharedPreferences
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val sharedPreferences: SharedPreferences) :
    ViewModel() {
    private var _login = MutableLiveData<Boolean>()
    val login: LiveData<Boolean> get() = _login

    fun getLogin() {
        _login.value = sharedPreferences.getLogin()
    }
}