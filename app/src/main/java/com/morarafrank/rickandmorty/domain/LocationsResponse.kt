package com.morarafrank.rickandmorty.domain

data class LocationsResponse(
    val info: PageInfo,
    val results: List<LocationResponse>
)


data class LocationResponse(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>,
    val url: String,
    val created: String
)
