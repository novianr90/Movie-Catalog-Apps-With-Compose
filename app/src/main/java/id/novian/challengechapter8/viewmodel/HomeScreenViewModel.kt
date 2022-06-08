package id.novian.challengechapter8.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import id.novian.challengechapter8.helper.Resource
import id.novian.challengechapter8.helper.SharedPreferences
import id.novian.challengechapter8.repository.LocalRepository
import id.novian.challengechapter8.repository.NetworkRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject
constructor(
    private val networkRepository: NetworkRepository,
    private val sharedPreferences: SharedPreferences,
    private val localRepository: LocalRepository
) : ViewModel() {

    fun getPopularMovies() = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(data = networkRepository.getMoviePopular()))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred"))
        }
    }

    fun getEmail() = liveData { emit(sharedPreferences.getEmail()) }

    fun getUsername(email: String) =
        liveData(Dispatchers.IO) { emit(localRepository.getProfileByEmail(email)) }
}