package id.novian.challengechapter8.repository

import id.novian.challengechapter8.BuildConfig
import id.novian.challengechapter8.model.network.client.ApiService

class NetworkRepository(private val apiService: ApiService) {
    private val apiKey = BuildConfig.API_KEY

    suspend fun getMoviePopular() = apiService.getPopularMovie(apiKey)
    suspend fun getDetailsById(id: Int) = apiService.getDetailsById(apiKey, id)
}