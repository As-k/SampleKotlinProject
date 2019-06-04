package com.ajaring_kotlin.util

import com.ajaring_kotlin.model.StandardResult
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


/**
 * Created by Ashish on 2/4/19.
 */

interface ApiInterface {

    @FormUrlEncoded
    @POST("login")
    fun callLoginApi(@FieldMap() inputs: HashMap<String, String>): Call<StandardResult>

    @FormUrlEncoded
    @POST("register")
    fun callRegisterApi(@FieldMap() inputs: HashMap<String, String>): Call<StandardResult>

    @FormUrlEncoded
    @POST("search_cars")
    fun callSearchCars(@FieldMap() inputs: HashMap<String, String>): Call<StandardResult>


}