package id.novian.challengechapter8.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.novian.challengechapter8.helper.SharedPreferences
import id.novian.challengechapter8.model.local.source.MoviePopularResponseSource
import id.novian.challengechapter8.model.network.model.popular.MoviePopularResponse
import id.novian.challengechapter8.repository.LocalRepository
import id.novian.challengechapter8.repository.NetworkRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject
constructor(
    private val networkRepository: NetworkRepository,
    private val sharedPreferences: SharedPreferences,
    private val localRepository: LocalRepository
) : ViewModel() {

    var movieState by mutableStateOf(emptyList<MoviePopularResponseSource>())
        private set

    var dataError by mutableStateOf("")
        private set

    var username by mutableStateOf("")

    var usernameError by mutableStateOf("")
        private set

    private var _data = MutableLiveData<MoviePopularResponse>()

    private fun doCall() {
        viewModelScope.launch {
            _data.postValue(networkRepository.getMoviePopular())
        }
    }

    init {
        doCall()
    }

    fun insertMovie() {
        val data = _data.value!!.results

        viewModelScope.launch {
            for (i in data.indices) {
                localRepository.insertMovie(
                    MoviePopularResponseSource(
                        data[i].id,
                        data[i].title,
                        data[i].posterPath,
                        data[i].releaseDate
                    )
                )
            }
        }
    }

    fun getMovie() {
        viewModelScope.launch {
            try {
                movieState = localRepository.getMovieSource()!!
            } catch (e: Exception) {
                dataError = e.message!!
            }
        }
    }

    fun getUsername() {
        val email = sharedPreferences.getEmail()

        if (email != null) {
            try {
                viewModelScope.launch {
                    username = localRepository.getUsernameByEmail(email)!!
                }
            } catch (e: Exception) {
                usernameError = e.message ?: "Error Occurred"
            }
        } else {
            usernameError = "Error"
        }
    }

    fun sessionClear() {
        sharedPreferences.sessionDelete()
    }
}