package com.example.repidapi.data

import com.google.gson.annotations.SerializedName

data class SearchQueryResponse (

    val query : String,
    val website : String,
    @SerializedName("searched_results")
    val searchedResults : Long,
    val position : Long
)