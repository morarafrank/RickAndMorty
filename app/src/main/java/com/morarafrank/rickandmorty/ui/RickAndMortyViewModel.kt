package com.morarafrank.rickandmorty.ui


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.morarafrank.rickandmorty.data.repo.RickAndMortyRepository
import com.morarafrank.rickandmorty.domain.CharacterResponse
import com.morarafrank.rickandmorty.ui.screens.EpisodeResponse
import com.morarafrank.rickandmorty.ui.screens.LocationResponse
import com.morarafrank.rickandmorty.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RickAndMortyViewModel @Inject constructor(
    private val repository: RickAndMortyRepository
) : ViewModel() {

    // --------------------- CHARACTERS ---------------------
    private val _characters = MutableStateFlow<Resource<List<CharacterResponse>>>(Resource.Loading())
    val characters: StateFlow<Resource<List<CharacterResponse>>> = _characters

    private var currentCharacterPage = 1

    fun loadCharacters(page: Int = 1) {
        viewModelScope.launch {
            _characters.value = Resource.Loading()
            try {
                repository.getCharacters(page).collect { response ->
                    _characters.value = Resource.Success(response.results)
                }
                currentCharacterPage = page
            } catch (e: Exception) {
                _characters.value = Resource.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    fun loadNextCharacterPage() = loadCharacters(currentCharacterPage + 1)

    fun getCharacterById(id: Int) = flow {
        emit(Resource.Loading<CharacterResponse>())
        try {
            val character = repository.getCharacter(id)
            emit(Resource.Success(character))
        } catch (e: Exception) {
            emit(Resource.Error<CharacterResponse>(e.localizedMessage ?: "Unknown error"))
        }
    }

    fun getMultipleCharactersByIds(ids: List<Int>) = flow {
        emit(Resource.Loading<List<CharacterResponse>>())
        try {
            val characters = repository.getMultipleCharacters(ids)
            emit(Resource.Success(characters))
        } catch (e: Exception) {
            emit(Resource.Error<List<CharacterResponse>>(e.localizedMessage ?: "Unknown error"))
        }
    }



    // --------------------- LOCATIONS ---------------------
    private val _locations = MutableStateFlow<Resource<List<LocationResponse>>>(Resource.Loading())
    val locations: StateFlow<Resource<List<LocationResponse>>> = _locations

    private var currentLocationPage = 1

    fun loadLocations(page: Int = 1) {
        viewModelScope.launch {
            _locations.value = Resource.Loading()
            try {

                repository.getLocations(page).collect { response ->
                    _locations.value = Resource.Success(response.results)
                }
                currentLocationPage = page
            } catch (e: Exception) {
                _locations.value = Resource.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    fun loadNextLocationPage() = loadLocations(currentLocationPage + 1)

    fun getLocationById(id: Int) = flow {
        emit(Resource.Loading<LocationResponse>())
        try {
            val location = repository.getLocation(id)
            emit(Resource.Success(location))
        } catch (e: Exception) {
            emit(Resource.Error<LocationResponse>(e.localizedMessage ?: "Unknown error"))
        }
    }

    fun getMultipleLocationsByIds(ids: List<Int>) = flow {
        emit(Resource.Loading<List<LocationResponse>>())
        try {
            val locations = repository.getMultipleLocations(ids)
            emit(Resource.Success(locations))
        } catch (e: Exception) {
            emit(Resource.Error<List<LocationResponse>>(e.localizedMessage ?: "Unknown error"))
        }
    }

    // --------------------- EPISODES ---------------------
    private val _episodes = MutableStateFlow<Resource<List<EpisodeResponse>>>(Resource.Loading())
    val episodes: StateFlow<Resource<List<EpisodeResponse>>> = _episodes

    private var currentEpisodePage = 1

    fun loadEpisodes(page: Int = 1) {
        viewModelScope.launch {
            _episodes.value = Resource.Loading()
            try {

                repository.getEpisodes(page).collect { response ->
                    _episodes.value = Resource.Success(response.results)
                }
                currentEpisodePage = page
            } catch (e: Exception) {
                _episodes.value = Resource.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    fun loadNextEpisodePage() = loadEpisodes(currentEpisodePage + 1)

    fun getEpisodeById(id: Int) = flow {
        emit(Resource.Loading<EpisodeResponse>())
        try {
            val episode = repository.getEpisode(id)
            emit(Resource.Success(episode))
        } catch (e: Exception) {
            emit(Resource.Error<EpisodeResponse>(e.localizedMessage ?: "Unknown error"))
        }
    }

    fun getMultipleEpisodesByIds(ids: List<Int>) = flow {
        emit(Resource.Loading<List<EpisodeResponse>>())
        try {
            val episodes = repository.getMultipleEpisodes(ids)
            emit(Resource.Success(episodes))
        } catch (e: Exception) {
            emit(Resource.Error<List<EpisodeResponse>>(e.localizedMessage ?: "Unknown error"))
        }
    }


    // ---------------- UTILITY ----------------
    fun refreshAll() {
        loadCharacters()
        loadLocations()
        loadEpisodes()
    }
}

