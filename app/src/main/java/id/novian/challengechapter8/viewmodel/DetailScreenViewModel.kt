package id.novian.challengechapter8.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.novian.challengechapter8.model.network.model.detail.MovieDetailsResponse
import id.novian.challengechapter8.repository.NetworkRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel
@Inject constructor(private val networkRepository: NetworkRepository) : ViewModel() {

    private var _data = MutableLiveData<MovieDetailsResponse>()
    val data: LiveData<MovieDetailsResponse> get() = _data

    fun getDetailsMovieById(id: Int) {
        viewModelScope.launch {
            _data.postValue(networkRepository.getDetailsById(id))
        }
    }

}