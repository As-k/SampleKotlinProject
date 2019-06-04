package com.ajaring_kotlin.presenter

import android.content.Context
import com.ajaring_kotlin.model.StandardResult
import com.ajaring_kotlin.util.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by Ashish on 4/6/19.
 */

class SearchingCarPresenter(context: Context, searchingCarListener: SearchingCarListener) {
    val TAG: String by lazy { "LoginPresenter" }
    var searchingCarListener: SearchingCarListener
    var context: Context

    init {
        this.context = context
        this.searchingCarListener = searchingCarListener
    }

    fun searchingCar(inputs: HashMap<String, String>) {
        searchingCarListener.onSentRequest()
        /*val standardResult: Call<StandardResult> =*/
        ApiClient(context).getServiceApi().callSearchCars(inputs).enqueue(object : Callback<StandardResult> {
            override fun onFailure(call: Call<StandardResult>, t: Throwable) {
                searchingCarListener.onFailure(t.toString())
            }

            override fun onResponse(call: Call<StandardResult>, response: Response<StandardResult>) {

            }

        })
    }

}