package com.example.repidapi.resultat

data class Result(
    val title: String,
    val link: String,
    val description: String,
    val additional_links: List<AdditionalLink>,
    val cite: Cite
)