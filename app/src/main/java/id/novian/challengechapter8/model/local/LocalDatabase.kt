package id.novian.challengechapter8.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.novian.challengechapter8.model.local.profile.Profile
import id.novian.challengechapter8.model.local.profile.ProfileDao
import id.novian.challengechapter8.model.local.source.MoviePopularResponseSource
import id.novian.challengechapter8.model.local.source.MoviePopularResponseSourceDao

@Database(entities = [Profile::class, MoviePopularResponseSource::class], version = 2)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun profileDao(): ProfileDao
    abstract fun movieSourceDao(): MoviePopularResponseSourceDao

    companion object {
        private var INSTANCE: LocalDatabase? = null

        fun getInstance(context: Context): LocalDatabase? {
            if (INSTANCE == null) {
                synchronized(LocalDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext, LocalDatabase::class.java, "Local.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}