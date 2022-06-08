package id.novian.challengechapter8.helper

import android.content.Context

class SharedPreferences(context: Context) {
    private val sharedPref = context.getSharedPreferences(PREFERENCES, 0)
    private val editor = sharedPref.edit()

    suspend fun saveEmail(value: String) {
        editor.apply {
            putString(EMAIL_KEY, value)
            apply()
        }
    }

    suspend fun saveStatusLogin() {
        editor.apply {
            putBoolean(LOGIN_KEY, true)
            apply()
        }
    }

    fun getEmail() = sharedPref.getString(EMAIL_KEY, "")
    fun getLogin() = sharedPref.getBoolean(LOGIN_KEY, false)

    fun sessionDelete() {
        editor.apply {
            clear()
            apply()
        }
    }

    companion object {
        private const val PREFERENCES = "pref"
        const val LOGIN_KEY = "login"
        const val EMAIL_KEY = "email"
    }
}