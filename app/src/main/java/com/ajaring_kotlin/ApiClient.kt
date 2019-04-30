package com.ajaring_kotlin

import android.content.Context
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.util.concurrent.TimeUnit


/**
 * Created by Ashish on 2/4/19.
 */
class ApiClient(context: Context) {

    private var mContext: Context

//    var instance: ApiClient

    //    @Synchronized
//    fun getInstance(): ApiClient {
//        if (instance == null)
//            instance = ApiClient()
//        return instance as ApiClient
//    }
    init {
        this.mContext = context
    }

    val BASE_URL: String = "http://34.197.60.238/ajaring/index.php/rest/"
    var retrofit: Retrofit? = null
    lateinit var applicationPreferences: ApplicationPreferences
    fun getClient(): Retrofit {
        applicationPreferences = ApplicationPreferences(mContext)
        if (retrofit == null) {
            try {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getOKClient())
                    .build()
            } catch (e: KeyManagementException) {
                e.printStackTrace()
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
            }

        }
        return retrofit!!
    }


    fun getOKClient(): OkHttpClient {
//        val DISK_CACHE_SIZE = 50 * 1024 * 1024 // 50MB
        // Install an HTTP cache in the context cache directory.
//        val cacheDir = File(mContext.cacheDir, "http")
//        val cache = Cache(cacheDir, DISK_CACHE_SIZE.toLong())

        val httpClient = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .header("Content-Type", "application/json")
                .header("Authentication", applicationPreferences.getStringData("auth_key"))
                .method(original.method(), original.body())
                .build()
            chain.proceed(request)
        }
        httpClient.addNetworkInterceptor(interceptor)
        httpClient.readTimeout(1, TimeUnit.MINUTES)
        httpClient.connectTimeout(2, TimeUnit.MINUTES)

        return httpClient.build()
    }

    fun getServiceApi(): ApiInterface {
        return getClient().create(ApiInterface::class.java)
    }

}