package com.morarafrank.rickandmorty.data.repo

import com.morarafrank.rickandmorty.data.remote.RickAndMortyService
import com.morarafrank.rickandmorty.domain.CharacterResponse
import com.morarafrank.rickandmorty.domain.CharactersResponse
import com.morarafrank.rickandmorty.domain.EpisodeResponse
import com.morarafrank.rickandmorty.domain.EpisodesResponse
import com.morarafrank.rickandmorty.domain.LocationResponse
import com.morarafrank.rickandmorty.domain.LocationsResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RickAndMortyRepository @Inject constructor(
    private val rickAndMortyService: RickAndMortyService
){

    // --------------------- CHARACTERS ---------------------
    fun getCharacters(page: Int = 1): Flow<CharactersResponse> = flow {
        emit(rickAndMortyService.getCharacters(page))
    }

    suspend fun getCharacter(id: Int): CharacterResponse {
        return rickAndMortyService.getCharacter(id)
    }

    suspend fun getMultipleCharacters(ids: List<Int>): List<CharacterResponse> {
        return rickAndMortyService.getMultipleCharacters(ids)
    }

    // --------------------- LOCATIONS ---------------------
    fun getLocations(page: Int = 1): Flow<LocationsResponse> = flow {
        emit(rickAndMortyService.getLocations(page))
    }

    suspend fun getLocation(id: Int): LocationResponse {
        return rickAndMortyService.getLocation(id)
    }

    suspend fun getMultipleLocations(ids: List<Int>): List<LocationResponse> {
        return rickAndMortyService.getMultipleLocations(ids)
    }

    // --------------------- EPISODES ---------------------
    fun getEpisodes(page: Int = 1): Flow<EpisodesResponse> = flow {
        emit(rickAndMortyService.getEpisodes(page))
    }

    suspend fun getEpisode(id: Int): EpisodeResponse {
        return rickAndMortyService.getEpisode(id)
    }

    suspend fun getMultipleEpisodes(ids: List<Int>): List<EpisodeResponse> {
        return rickAndMortyService.getMultipleEpisodes(ids)
    }


}