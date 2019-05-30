package com.ajaring_kotlin.presenter

import android.content.Context
import android.util.Log
import com.ajaring_kotlin.model.Login
import com.ajaring_kotlin.model.StandardResult
import com.ajaring_kotlin.util.ApiClient
import com.ajaring_kotlin.util.AppContents
import com.ajaring_kotlin.util.ApplicationPreferences
import com.google.gson.Gson
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Ashish on 2/4/19.
 */

class LoginPresenter(context: Context, loginListener: LoginListener) {

    val TAG: String by lazy { "LoginPresenter" }
    var loginListener: LoginListener
    var context: Context

    init {
        this.context = context
        this.loginListener = loginListener
    }


    fun loginRequest(inputs: HashMap<String, String>) {
        Log.d("TAG", "" + inputs)
        val apiInterface = ApiClient(context).getServiceApi()
        val standardResult: Call<StandardResult> = apiInterface.callLoginApi(inputs)
        standardResult.enqueue(object : Callback<StandardResult> {
            override fun onFailure(call: Call<StandardResult>, t: Throwable) {
                loginListener.onFailure(t.toString())
            }

            override fun onResponse(call: Call<StandardResult>, response: Response<StandardResult>) {
                if (response.isSuccessful) {
                    val jsonElement = response.body()!!.data
                    if (jsonElement is JsonObject) {
                        val userData: Login = Gson().fromJson<Any>(jsonElement, Login::class.java) as Login
                        val preferenceStorage = ApplicationPreferences(context)
                        userData.name?.let { preferenceStorage.setStringData(AppContents().NAME, it) }
                        userData.email?.let { preferenceStorage.setStringData(AppContents().EMAIL, it) }
                        userData.id?.let { preferenceStorage.setStringData(AppContents().USER_ID, it) }
                        userData.authKey?.let { preferenceStorage.setStringData(AppContents().AUTH_KEY, it) }
                        userData.refreshToken?.let { preferenceStorage.setStringData(AppContents().REFRESH_TOKEN, it) }
                        userData.phone?.let { preferenceStorage.setStringData(AppContents().PHONE, it) }
                        userData.password?.let { preferenceStorage.setStringData(AppContents().PASSWORD, it) }
                        userData.custImagePath?.let { preferenceStorage.setStringData(AppContents().PROFILE_IMAGE, it) }
                        preferenceStorage.setStringData(AppContents().LANGUAGE, AppContents().ENGLISH)
                        userData.identityProof?.let {
                            preferenceStorage.setStringData(
                                AppContents().IDENTITY_IMAGE_PATH,
                                it
                            )
                        }
                        userData.licenceImagePath?.let {
                            preferenceStorage.setStringData(
                                AppContents().LICENCE_IMAGE_PATH,
                                it
                            )
                        }
                        loginListener.onSuccess(
                            response.body()?.status,
                            response.body()?.message,
                            response.body()?.data
                        )
                    }
                }
            }

        })


    }
/*
    fun invoke(mContext: Context) {
        context = mContext
    }*/


}


