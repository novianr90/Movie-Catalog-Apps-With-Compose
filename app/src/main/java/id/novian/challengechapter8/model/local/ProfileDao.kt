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

    @Query("SELECT username FROM Profile WHERE email = :email")
    fun getUsernameByEmail(email: String): String

    @Query("SELECT email FROM Profile WHERE email = :email")
    fun getEmail(email: String): String

    @Query("SELECT password FROM Profile WHERE password = :pass")
    fun getPassword(pass: String): String

    @Query("SELECT username FROM Profile WHERE username = :username")
    fun getUsername(username: String): String
}