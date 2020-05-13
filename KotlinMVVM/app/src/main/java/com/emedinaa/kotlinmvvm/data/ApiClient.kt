package com.emedinaa.kotlinmvvm.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object ApiClient {

    //https://obscure-earth-55790.herokuapp.com/api/museums
    private val API_BASE_URL = "https://obscure-earth-55790.herokuapp.com"

    val servicesApiInterface:ServicesApiInterface

    init {
        servicesApiInterface = build()
    }

    fun build():ServicesApiInterface{
        var builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        var httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor())

        var retrofit: Retrofit = builder.client(httpClient.build()).build()
        return retrofit.create(
            ServicesApiInterface::class.java)
    }

    private fun interceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level=HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    interface ServicesApiInterface{
        @GET("/api/museums/")
        suspend fun museums(): Response<MuseumResponse>
    }
}