package com.example.repidapi.resultat

import com.google.gson.annotations.SerializedName

open class SearchResult(
    val results: List<Result>,
    @SerializedName("image_results")
    val imageResults: List<ImageResult>,
    val total: Long,
    val answers: List<String>,
    val ts: Double,
    @SerializedName("device_region")
    val deviceRegion: String,
    @SerializedName("device_type")
    val deviceType: String,
    val html : String
)