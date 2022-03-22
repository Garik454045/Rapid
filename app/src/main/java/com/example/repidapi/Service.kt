package com.example.repidapi

import com.example.repidapi.data.SearchQueryRequestBody
import com.example.repidapi.data.SearchQueryResponse
import com.example.repidapi.resultat.SearchResult
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Service {

    @GET("api/v1/search/q={query}")
    fun rapidSearch(@Path("query") searchString: String): Call<SearchResult>

    @GET("api/v1/images/q={query}")
    fun rapidImage(@Path("query") searchString: String): Call<SearchResult>

    @GET("api/v1/scholar/q={query}")
    fun rapidScholar(@Path("query") searchString: String): Call<SearchResult>

    @POST("api/v1/serp/")
    fun rapidSerp(@Body searchQueryRequestBody: SearchQueryRequestBody): Call<SearchQueryResponse>
}