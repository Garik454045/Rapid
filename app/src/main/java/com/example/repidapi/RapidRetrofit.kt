package com.example.repidapi

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RapidRetrofit {

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {

                val original = chain.request()
                val request = original.newBuilder()
                    .addHeader("x-user-agent", "android")
                    .addHeader("x-proxy-location", "ARM")
                    .addHeader("x-rapidapi-host", "google-search3.p.rapidapi.com")
                    .addHeader(
                        "x-rapidapi-key",
                        "579f6bb850msh3624e11db3c344bp1c6427jsnd0d5f5e29fae"
                    )
                    .build()

                return chain.proceed(request)
            }
        })
        .build()

    val retrofitRapid: Retrofit = Retrofit.Builder()
        .baseUrl("https://google-search3.p.rapidapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
}

