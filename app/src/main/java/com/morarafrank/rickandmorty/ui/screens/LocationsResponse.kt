package com.morarafrank.rickandmorty.ui.screens

data class LocationsResponse(
    val info: PageInfo,
    val results: List<LocationResponse>
)

data class PageInfo(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
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
