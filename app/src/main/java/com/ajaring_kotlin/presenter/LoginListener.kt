package com.ajaring_kotlin.presenter

import com.google.gson.JsonElement


/**
 * Created by Ashish on 2/4/19.
 */

interface LoginListener {
    abstract fun onSentRequest()
    abstract fun onSuccess(status: Boolean?, message: String?, data: JsonElement?)
    abstract fun onPendingVerified(status: Boolean, message: String, mobile: String, isVerified: Boolean)
    abstract fun onFailure()
}