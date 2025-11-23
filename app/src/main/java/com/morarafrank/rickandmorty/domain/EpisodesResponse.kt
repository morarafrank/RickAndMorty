package com.morarafrank.rickandmorty.domain

data class EpisodesResponse(
    val info: PageInfo,
    val results: List<EpisodeResponse>
)

data class EpisodeResponse(
    val id: Int,
    val name: String,
    val air_date: String,
    val episode: String, // like "S01E01"
    val characters: List<String>, // URLs to characters
    val url: String,
    val created: String
)

