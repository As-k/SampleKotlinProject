package com.ajaring_kotlin.presenter

import com.google.gson.JsonElement


/**
 * Created by Ashish on 4/6/19.
 */

interface SearchingCarListener {
    abstract fun onSentRequest()
    abstract fun onSuccess(status: Boolean?, message: String?, data: JsonElement?)
    abstract fun onFailure(errorMsg: String)
}