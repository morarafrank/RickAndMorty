package com.morarafrank.rickandmorty.data.remote

import com.morarafrank.rickandmorty.domain.CharacterResponse
import com.morarafrank.rickandmorty.domain.CharactersResponse
import com.morarafrank.rickandmorty.domain.EpisodeResponse
import com.morarafrank.rickandmorty.domain.EpisodesResponse
import com.morarafrank.rickandmorty.domain.LocationResponse
import com.morarafrank.rickandmorty.domain.LocationsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyService {
    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int): CharactersResponse

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): CharacterResponse

    @GET("character/{ids}")
    suspend fun getMultipleCharacters(
        @Path("ids") ids: List<Int>
    ): List<CharacterResponse>

    @GET("location")
    suspend fun getLocations(@Query("page") page: Int = 1): LocationsResponse

    @GET("location/{id}")
    suspend fun getLocation(@Path("id") id: Int): LocationResponse

    @GET("location/{ids}")
    suspend fun getMultipleLocations(
        @Path("ids") ids: List<Int>
    ): List<LocationResponse>

    @GET("episode")
    suspend fun getEpisodes(@Query("page") page: Int = 1): EpisodesResponse

    @GET("episode/{id}")
    suspend fun getEpisode(@Path("id") id: Int): EpisodeResponse

    @GET("episode/{ids}")
    suspend fun getMultipleEpisodes(
        @Path("ids") ids: List<Int>
    ): List<EpisodeResponse>


}
