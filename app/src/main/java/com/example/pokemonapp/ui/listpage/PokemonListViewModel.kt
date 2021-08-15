package com.example.pokemonapp.ui.listpage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.data.usecase.GetPokemonListUseCase
import com.example.pokemonapp.model.ApiResult
import com.example.pokemonapp.model.PokemonResponse
import com.example.pokemonapp.util.Event
import com.example.pokemonapp.util.EventType
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PokemonListViewModel : ViewModel() {

    private val _pokeList = MutableLiveData<List<PokemonListItemViewState>>()
    val pokeList: LiveData<List<PokemonListItemViewState>> = _pokeList

    private val _event = MutableLiveData<Event<EventType>>()
    val event: LiveData<Event<EventType>> = _event

    init {
        getPokeList()
    }

    fun getPokeList() {
        viewModelScope.launch {
            _event.value = Event(EventType.ShowProgress)
            GetPokemonListUseCase.getPokeList().collect {
                _event.value = Event(EventType.HideProgress)
                when (it) {
                    is ApiResult.Success -> {
                        _pokeList.value = it.data.mapToViewState()
                    }
                    is ApiResult.Error -> _event.value = Event(EventType.Error(it.error))
                }
            }
        }
    }

    fun List<PokemonResponse>.mapToViewState() = ArrayList<PokemonListItemViewState>().apply {
        for (poke in this@mapToViewState) {
            add(PokemonListItemViewState(poke.id, poke.name, poke.description, poke.imageUrl))
        }
    }
}