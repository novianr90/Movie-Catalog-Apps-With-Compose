package id.novian.challengechapter8.repository

import android.content.Context
import id.novian.challengechapter8.model.local.LocalDatabase
import id.novian.challengechapter8.model.local.Profile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalRepository(context: Context) {
    private val db = LocalDatabase.getInstance(context)

    suspend fun insertProfile(profile: Profile) = withContext(Dispatchers.IO) {
        db?.profileDao()?.insertProfile(profile)
    }

    suspend fun getProfileByEmail(email: String) = withContext(Dispatchers.IO) {
        db?.profileDao()?.getProfile(email)
    }

    suspend fun getEmail(email: String) = withContext(Dispatchers.IO) {
        db?.profileDao()?.getEmail(email)
    }

    suspend fun getPassword(pass: String) = withContext(Dispatchers.IO) {
        db?.profileDao()?.getPassword(pass)
    }

    suspend fun getUsername(username: String) = withContext(Dispatchers.IO) {
        db?.profileDao()?.getUsername(username)
    }

    suspend fun getUsernameByEmail(email: String) = withContext(Dispatchers.IO) {
        db?.profileDao()?.getUsernameByEmail(email)
    }
}