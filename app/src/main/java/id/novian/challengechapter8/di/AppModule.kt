package id.novian.challengechapter8.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.novian.challengechapter8.helper.SharedPreferences
import id.novian.challengechapter8.model.network.client.ApiClient
import id.novian.challengechapter8.model.network.client.ApiService
import id.novian.challengechapter8.repository.LocalRepository
import id.novian.challengechapter8.repository.NetworkRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApiService() = ApiClient.instance

    @Singleton
    @Provides
    fun provideNetworkRepository(apiService: ApiService) = NetworkRepository(apiService)

    @Provides
    fun provideSharedPref(@ApplicationContext context: Context) = SharedPreferences(context)

    @Provides
    fun provideLocalRepository(@ApplicationContext context: Context) = LocalRepository(context)
}