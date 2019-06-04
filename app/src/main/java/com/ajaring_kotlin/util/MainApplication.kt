package com.ajaring_kotlin.util

import android.app.Application
import android.content.Context
import android.provider.Settings
import android.text.TextUtils
import android.util.Patterns


/**
 * Created by Ashish on 1/4/19.
 */

class MainApplication : Application() {

    private var mInstance: MainApplication? = null
    private lateinit var applicationPreferences: ApplicationPreferences

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        applicationPreferences = ApplicationPreferences(baseContext)
    }

    @Synchronized
    fun getInstance(): MainApplication? {
        return mInstance?.let { mInstance }
    }

    fun isValidEmail(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    fun androidId(context: Context): String {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    }
}