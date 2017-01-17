package com.levibostian.lifefeed.module

import com.levibostian.lifefeed.MainApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.levibostian.lifefeed.AppConstants
import com.levibostian.lifefeed.deserializer.DateDeserializer
import com.levibostian.lifefeed.manager.UserCredsManager
import com.levibostian.lifefeed.service.GitHubService
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.*

@Module open class ApiModule(val application: MainApplication) {

    @Provides
    @Singleton
    fun provideRetrofit(credsManager: UserCredsManager): Retrofit {
        val client = OkHttpClient.Builder()
                .addNetworkInterceptor { chain ->
                    val authToken = credsManager.getAuthToken()

                    if (authToken != null) {
                        val request = chain.request()
                                .newBuilder()
                                .addHeader("Access-Token", credsManager.getAuthToken())
                                .build()

                        chain.proceed(request)
                    } else {
                        chain.proceed(chain.request())
                    }
                }
                .build()

        val gson = GsonBuilder()
                .registerTypeAdapter(Date::class.java, DateDeserializer())
                .create()

        return Retrofit.Builder()
                .client(client).baseUrl(AppConstants.API_ENDPOINT)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson)).build()
    }

    @Provides
    fun provideService(retrofit: Retrofit): GitHubService {
        return retrofit.create(GitHubService::class.java)
    }

}