package id.novian.challengechapter8.model.local.source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MoviePopularResponseSourceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(moviePopularResponseSource: MoviePopularResponseSource)

    @Query("SELECT * FROM MOVIE_SOURCE")
    fun getMovieSource(): List<MoviePopularResponseSource>
}