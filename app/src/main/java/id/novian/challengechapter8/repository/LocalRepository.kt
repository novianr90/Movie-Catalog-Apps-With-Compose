package id.novian.challengechapter8.repository

import android.content.Context
import id.novian.challengechapter8.model.local.LocalDatabase
import id.novian.challengechapter8.model.local.profile.Profile
import id.novian.challengechapter8.model.local.source.MoviePopularResponseSource
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

    //Movie
    suspend fun insertMovie(moviePopularResponseSource: MoviePopularResponseSource) =
        withContext(Dispatchers.IO) {
            db?.movieSourceDao()?.insert(moviePopularResponseSource)
        }

    suspend fun getMovieSource() = withContext(Dispatchers.IO) {
        db?.movieSourceDao()?.getMovieSource()
    }
}