package com.example.meridianproject.utils

import android.content.Context
import android.content.SharedPreferences



class PrefManager(context: Context) {
    private val sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.getSharedPreferences(TAG_NAME, Context.MODE_PRIVATE)
    }
    companion object {
        private const val TAG_NAME = "meridian.prefs"

        private const val Email = "email"
        private const val PassWord="password"
        private const val Name = "name"

    }
    private fun putString(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    private fun putLong(key: String, value: Long) {
        val editor = sharedPreferences.edit()
        editor.putLong(key, value)
        editor.apply()
    }

    private fun putInt(key: String, value: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    private fun putBoolean(key: String, value: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }
    fun clearData() {
        sharedPreferences.edit().clear().apply()
    }


    var checkEmail: String?
        get() = sharedPreferences.getString(Email, "")
        set(email) = putString(Email, email!!)

    var checkPassword: String?
        get() = sharedPreferences.getString(PassWord, "")
        set(password) = putString(PassWord, password!!)

    var checkName:String?
       get()=sharedPreferences.getString(Name,"")
       set(name)=putString(Name,name!!)


}