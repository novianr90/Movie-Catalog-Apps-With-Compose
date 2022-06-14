package id.novian.challengechapter8.model.network.client

import id.novian.challengechapter8.model.network.model.detail.MovieDetailsResponse
import id.novian.challengechapter8.model.network.model.popular.MoviePopularResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getPopularMovie(@Query("api_key") apiKey: String): MoviePopularResponse

    @GET("movie/{id}")
    suspend fun getDetailsById(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): MovieDetailsResponse
}