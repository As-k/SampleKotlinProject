package com.ajaring_kotlin

import android.content.Context
import android.content.SharedPreferences


/**
 * Created by Ashish on 1/4/19.
 */

class ApplicationPreferences(mContext: Context) {

    val sharedPreferences: SharedPreferences = mContext.getSharedPreferences(
        mContext.getString(R.string.app_name), Context.MODE_PRIVATE)

    lateinit var editor: SharedPreferences.Editor

    fun setStringData(key: String, value: String) {
        editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getStringData(key: String): String ?{
        return sharedPreferences.getString(key, "")
    }

    fun setBooleanData(key: String, value: Boolean) {
        editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getBooleanData(key: String): Boolean? {
        return sharedPreferences.getBoolean(key, false)
    }

    fun clearAllData() {
        editor = sharedPreferences.edit()
        editor.clear()
        editor.commit()
    }

}