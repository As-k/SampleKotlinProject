package com.ajaring_kotlin.presenter

import android.content.Context
import com.ajaring_kotlin.ApiClient
import com.ajaring_kotlin.ApiInterface
import com.ajaring_kotlin.ApplicationPreferences
import com.ajaring_kotlin.model.Login
import com.ajaring_kotlin.model.StandardResult
import com.ajaring_kotlin.view.fragment.LoginFragment
import com.google.gson.Gson
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Ashish on 2/4/19.
 */

class LoginPresenter(context: Context) {

    var loginListener: LoginListener? = null
    var context: Context

    init {
        this.context = context
    }



    fun loginRequest(inputs: HashMap<String, String>) {

        val apiInterface = ApiClient(context).getServiceApi()
        val standardResult: Call<StandardResult> = apiInterface.callLoginApi(inputs)
        standardResult.enqueue(object : Callback<StandardResult> {
            override fun onFailure(call: Call<StandardResult>, t: Throwable) {
                loginListener?.onFailure()
            }

            override fun onResponse(call: Call<StandardResult>, response: Response<StandardResult>) {
                if (response.isSuccessful) {
                    val jsonElement = response.body()!!.data
                    if (jsonElement is JsonObject) {
                        val userData: Login = Gson().fromJson<Any>(jsonElement, Login::class.java) as Login
                        val preferenceStorage = ApplicationPreferences(context)
                        userData.name?.let { preferenceStorage.setStringData("name", it) }
                    }
                    loginListener?.onSuccess(response.body()?.status, response.body()?.message, response.body()?.data)
                }
            }

        })


    }
/*
    fun invoke(mContext: Context) {
        context = mContext
    }*/


}


