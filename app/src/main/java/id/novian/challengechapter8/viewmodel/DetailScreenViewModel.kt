package id.novian.challengechapter8.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import id.novian.challengechapter8.helper.Resource
import id.novian.challengechapter8.repository.NetworkRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel
@Inject constructor(private val networkRepository: NetworkRepository) : ViewModel() {

    fun getDetailsMovieById(id: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))

        try {
            emit(Resource.success(data = networkRepository.getDetailsById(id)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred"))
        }
    }
}