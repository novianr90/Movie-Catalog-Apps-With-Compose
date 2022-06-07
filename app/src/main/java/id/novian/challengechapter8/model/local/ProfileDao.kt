package id.novian.challengechapter8.model.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProfileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProfile(profile: Profile): Long

    @Query("SELECT * FROM Profile WHERE email = :email")
    fun getProfile(email: String): Profile
}