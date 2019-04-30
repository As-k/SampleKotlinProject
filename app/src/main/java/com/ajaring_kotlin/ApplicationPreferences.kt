package com.ajaring_kotlin

import android.content.Context
import android.content.SharedPreferences


/**
 * Created by Ashish on 1/4/19.
 */

class ApplicationPreferences(var mContext: Context) {

    var sharedPreferences = mContext.getSharedPreferences(
        mContext.getString(R.string.app_name), Context.MODE_PRIVATE
    )

    lateinit var editor: SharedPreferences.Editor

    fun setStringData(key: String, value: String) {
        with(sharedPreferences.edit()) {
            putString(key, value)
            apply()
        }
    }

    fun getStringData(key: String): String? {
        return sharedPreferences.getString(key, "")
    }

    fun setBooleanData(key: String, value: Boolean) {
        with(sharedPreferences.edit()) {
            putBoolean(key, value)
            apply()
        }
    }

    fun getBooleanData(key: String): Boolean? {
        return sharedPreferences.getBoolean(key, false)
    }

    fun removeValue(KEY_NAME: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.remove(KEY_NAME)
        editor.apply()
    }

    fun clearAllData() {
        with(sharedPreferences.edit()) {
            clear()
            apply()
        }
    }

}